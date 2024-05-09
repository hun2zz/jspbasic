<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page import="java.util.*" %> <%@ page
import="com.jsp.entity.Dancer" %> <%! // !를 붙이면 필드를 선언할 수 있음 public
static List<Dancer>
  dancerList = new ArrayList<>(); %> <% request.setCharacterEncoding("utf-8");
  String name = request.getParameter("name"); String crewName =
  request.getParameter("crewName"); String danceLevel =
  request.getParameter("danceLevel"); String[] genres =
  request.getParameterValues("genres"); List<Dancer.Genre>
    genreList = new ArrayList<>(); for (String genre : genres) {
    genreList.add(Dancer.Genre.valueOf(genre)); } dancer.setGenres(genreList);
    System.out.println("댄서 정보 등록중...."); System.out.println("dancer = " +
    dancer); dancerList.add(dancer); %>
    <!DOCTYPE html>
    <html lang="ko">
      <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Document</title>
      </head>
      <body>
        <h1><%= dancer.getName() %>님이 등록되었습니다</h1>
        <a href="/chap02/dancer/show-list">댄서 정보 모아보깅~~</a>
      </body>
    </html>
  </Dancer.Genre>
</Dancer>
