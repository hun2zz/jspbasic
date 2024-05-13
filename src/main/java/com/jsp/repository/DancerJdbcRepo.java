package com.jsp.repository;


import com.jsp.chap05.Person;
import com.jsp.entity.Dancer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.jsp.chap02.DancerSaveProcessServlet.dancerList;

// 실제 db임.
//역할 : 메모리 데이터베이스에 댄서들을 CRUD
// model 이라고 부름.
public class DancerJdbcRepo {
    private String username = "root"; // db 계정명
    private String password = "mariadb"; //db  패스워드
    private String url = "jdbc:mariadb://localhost:3306/spring5"; // db 설치 위치
    private String driverClassName = "org.mariadb.jdbc.Driver"; // db 회사별 전용 커넥터 클래스


    private static DancerJdbcRepo repo = new DancerJdbcRepo();

    // 싱글톤 구현
    private DancerJdbcRepo() {
    }

    //싱글 객체를 리턴하는 메서드
    public static DancerJdbcRepo getInstance() {
        return repo;
    }
    //데이터베이스 역할을 할 자료구조

    //댄서를 데이터베이스에 저장하는 기능
    public boolean save(Dancer dancer) {
        // 1. 연결 드라이버 로딩
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Class.forName(driverClassName);

            //2. 데이터베이스 접속
//            Connection conn = DriverManager.getConnection(url, username, password);
//            System.out.println("conn = " + conn);

            //3. 실행할 SQL 생성
            String sql = "INSERT INTO tbl_dancer" + "(name, crew_name, dance_level)" + "VALUES (?,?,?)";

            // 4. SQL 실행 객체 생성
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            //5. ? 값 채우기
            preparedStatement.setString(1, dancer.getName());
            preparedStatement.setString(2, dancer.getCrewName());
            preparedStatement.setString(3, dancer.getDanceLevel().toString());

            //6. 실행 명령
            // INSERT, UPDATE, DELETE는 모두 같은 명령어를 사용함
            // select만 다른 명령ㅇ어 사용

            preparedStatement.executeUpdate();
            return true;

            //7. 데이터베이스 연결 해제

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //댄서 리스트를 반환하는 기능

    public List<Dancer> getDancerList() {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Class.forName(driverClassName);

            String sql = "SELECT * FROM tbl_dancer";

            // SQL 실행 객체 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            //? 채우기 -> 비어있기 때문에 안 채워도 상관없ㄷ음

            //실행 명령 - SELECT는 다른 메서드를 사용함.
            // ResultSet = select의 결과집합 표를 가져온다.
            ResultSet rs = pstmt.executeQuery();

            //ResultSet 데이터 가져오기
            List<Dancer> danceList = new ArrayList<>();
            while (rs.next()) { // 표의 행을 지목하는 커서
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String crewName = rs.getString("crew_name");
                String danceLevel = rs.getString("dance_level");

                Dancer person = new Dancer();
                person.setId(id);
                person.setName(name);
                person.setCrewName(crewName);
                person.setDanceLevel(Dancer.DanceLevel.valueOf(danceLevel));

                danceList.add(person);
            }
            return danceList;


            //커서가 가르키는 행의 데이터를 하나 씩 추출 해야함.
//            int id = rs.getInt("id");
//            String personName = rs.getString("person_name");
//            int personAge = rs.getInt("person_age");
//
//            Person person = new Person(id, personName, personAge);
//            System.out.println("person = " + person);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void delete(String id) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Class.forName(driverClassName);
            String sql = "DELETE FROM tbl_dancer WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {

        }
    }
}
