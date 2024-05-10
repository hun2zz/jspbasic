package com.jsp.chap05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//마리아디비 연결 및 crud
public class JdbcBasic {
    //필요한 데이터들을 필드로 작성
    private String username = "root"; // db 계정명
    private String password = "mariadb"; //db  패스워드
    private String url = "jdbc:mariadb://localhost:3306/spring5"; // db 설치 위치
    private String driverClassName = "org.mariadb.jdbc.Driver"; // db 회사별 전용 커넥터 클래스

    // INSERT 기능
    public void insert (Person p) {
        // 1. 연결 드라이버 로딩
        try (Connection conn = DriverManager.getConnection(url, username, password)){
            Class.forName(driverClassName);

            //2. 데이터베이스 접속
//            Connection conn = DriverManager.getConnection(url, username, password);
//            System.out.println("conn = " + conn);

            //3. 실행할 SQL 생성
            String sql = "INSERT INTO tbl_person"+ "(id, person_name, person_age)"+"VALUES (?,?,?)";

            // 4. SQL 실행 객체 생성
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            //5. ? 값 채우기
            preparedStatement.setInt(1, p.getId());
            preparedStatement.setString(2, p.getPersonName());
            preparedStatement.setInt(3, p.getPersonAge());

            //6. 실행 명령
            // INSERT, UPDATE, DELETE는 모두 같은 명령어를 사용함
            // select만 다른 명령ㅇ어 사용

            preparedStatement.executeUpdate();

            //7. 데이터베이스 연결 해제

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete (int id) {
        // 1. 연결 드라이버 로딩
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Class.forName(driverClassName);

            //2. 데이터베이스 접속
//            Connection conn = DriverManager.getConnection(url, username, password);
//            System.out.println("conn = " + conn);

            //3. 실행할 SQL 생성
            String sql = "DELETE FROM tbl_person " + "WHERE id = ?";

            // 4. SQL 실행 객체 생성
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            //5. ? 값 채우기
            preparedStatement.setInt(1, id);

            //6. 실행 명령
            // INSERT, UPDATE, DELETE는 모두 같은 명령어를 사용함
            // select만 다른 명령ㅇ어 사용

            preparedStatement.executeUpdate();

            //7. 데이터베이스 연결 해제

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void update (int id, String newName, int newAge) {
        // 1. 연결 드라이버 로딩
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Class.forName(driverClassName);

            //2. 데이터베이스 접속
//            Connection conn = DriverManager.getConnection(url, username, password);
//            System.out.println("conn = " + conn);

            //3. 실행할 SQL 생성
            String sql = "UPDATE tbl_person "+
                    "SET person_name = ?, person_age =? "
                    +"WHERE id = ?";

            // 4. SQL 실행 객체 생성
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            //5. ? 값 채우기
            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, newAge);
            preparedStatement.setInt(3, id);

            //6. 실행 명령
            // INSERT, UPDATE, DELETE는 모두 같은 명령어를 사용함
            // select만 다른 명령ㅇ어 사용

            preparedStatement.executeUpdate();

            //7. 데이터베이스 연결 해제

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //SELECT
    public List<Person> findAll () {
        try (Connection conn = DriverManager.getConnection(url, username, password)){
            Class.forName(driverClassName);

            String sql = "SELECT * FROM tbl_person ORDER BY id DESC";

            // SQL 실행 객체 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            //? 채우기 -> 비어있기 때문에 안 채워도 상관없ㄷ음

            //실행 명령 - SELECT는 다른 메서드를 사용함.
            // ResultSet = select의 결과집합 표를 가져온다.
            ResultSet rs = pstmt.executeQuery();

            //ResultSet 데이터 가져오기
            List<Person> people = new ArrayList<>();
            while (rs.next()) { // 표의 행을 지목하는 커서
                int id = rs.getInt("id");
                String personName = rs.getString("person_name");
                int personAge = rs.getInt("person_age");

                Person person = new Person(id, personName, personAge);
                people.add(person);
            }
            return people;


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

}
