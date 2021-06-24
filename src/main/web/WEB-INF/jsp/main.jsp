<%--
  Created by IntelliJ IDEA.
  User: dkavialkou
  Date: 4/18/2021
  Time: 11:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html>
<head>
    <title>Main page</title>
</head>
<body>
<header>
    <h1 class="logo">Titllist</h1>
    <nav>
        <ul>
            <div class="dropdown">
                <li class="home">
                    <a href="#">
                        Home
                        <i class="fas fa-home"></i>
                    </a>
                </li>
                <div class="dropdown-content">
                    <div id="genre-dropdown">
                        <button id="genreBtn" type="button" class="genre" name="command">Genre
                        </button>
                        <div class="dropdown-content_genres dc" id="genres"></div>
                    </div>

                    <div id="type-dropdown">
                        <button id="typeBtn" type="button" class="type" name="command">Type
                        </button>
                        <div class="dropdown-content_types dc" id="types">
                        </div>
                    </div>

                    <div id="releaseDate-dropdown">
                        <button id="releaseDateBtn" type="button" class="releaseDate" name="command">Year
                        </button>
                        <div class="dropdown-content_releaseDate dc" id="releaseDates">
                        </div>
                    </div>
                    <a href="#">Rating</a>
                </div>
            </div>
            <li>
                <div class="search-icon">
                    <input class="search-txt" type="search" placeholder="Search">
                    <a class="search-btn" href="#">
                        <i class="fas fa-search"></i>
                    </a>
                </div>
            </li>

        </ul>
    </nav>
    <nav>
        <ul>
            <div class="dropdown">
                <i class="far fa-user-circle"></i>
                <li class="user">${sessionScope.user.userName}</li>
                <div class="dropdown-content">
                    <a href="#">My Titllist</a>
                    <button id="setting" type="button" class="setting">Settings</button>
                    <button type="button" class="adminSetting" id="adminSetting">Admin settings</button>
                    <button class="logout" name="command" onclick="location.href='controller?command=logout';">Log Out
                    </button>
                </div>
            </div>
        </ul>
    </nav>
</header>

<div id="modal" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <span class="close">&times;</span>
            <h2 align="center">Setting</h2>
        </div>
        <div class="modal-body">
            <p id="errorMessage">
            </p>
            <form id="changePasswordForm" class="changePassword" action="controller" method="post">
                <h2 class="sett_title" align="center">Change password</h2>
                <input id="currentPasswordField" type="password" name="currentPassword" placeholder="Current password"/>
                <input id="newPasswordField" type="password" name="newPassword" placeholder="New password"/>
                <input id="repeatNewPasswordField" type="password" name="repeatNewPassword"
                       placeholder="Repeat new password"/>
                <input type="hidden" name="command" value="changePasswordCommand">
                <input type="submit" value="Chang password"/>
            </form>
        </div>
    </div>
</div>


<div id="adminModal" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <span class="closeWindow">&times;</span>
            <h2 align="center">Admin settings</h2>
        </div>
        <div class="modal-body">
            <p id="errorMessagePlace">
            </p>
            <button id="addAnime">Add new anime</button>
            <button id="users">Users</button>
            <div id="usersBody" class="usersBody">
                <table id="userTable">
                    <tr>
                        <td>User</td>
                        <td>Role</td>
                    </tr>
                    <tr id="superAdmin">
                        <td>Lerkin</td>
                        <td>Super Admin</td>
                    </tr>
                </table>
                <form id='changeRole' action='controller' method='post'>
                    <input type="hidden" name="command" value="changeRole">
                    <input type="submit" id="saveRole" value="Save"/>
                </form>
            </div>
            <div id="addAnimeBody" class="addAnimeBody">
                <form id="addNewAnime" class="newAnime" action="controller" method="get">
                    <h2 class="sett_title" align="center">Add New Anime</h2>

                    <label>RusName: <input required class="animeField" name="rusName" type="text"/></label>

                    <label>JapName: <input required class="animeField" name="japName" type="text"/></label>

                    <label>Type:
                        <select form="addNewAnime" style="color: black" required id="typeSelection" name="type">
                        </select>
                    </label>

                    <label>Duration:
                        <input required class="animeField" name="duration" type="text"/>
                    </label>

                    <label>Episodes:
                        <input required class="animeField" name="episodes" type="text"/>
                    </label>

                    <label>Release Date:
                        <input required class="animeField" name="releaseDate" type="text"/>
                    </label>

                    <label>Genre:
                        <select form="addNewAnime" size="6" required id="genreSelection" multiple="multiple"
                                name="genre">

                        </select>
                    </label>

                    <input type="hidden" name="command" value="addNewAnime">
                    <input type="submit" value="Add"/>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="scroll">
    <div class="vertical-container" id="animeByTeg">
    </div>
</div>

<footer>

</footer>
</body>
<script defer src="http://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
    <%@include file="../js/main_page.js"%>
</script>
<script>
    <%@include file="../js/common.js"%>
</script>
<script>
    <%@include file="../js/modal_setting.js"%>
</script>
<style>
    <%@include file="../css/base.css"%>
</style>
<style>
    <%@include file="../css/main.css"%>
</style>
<style>
    <%@include file="../css/modal_setting.css"%>
</style>
</html>
