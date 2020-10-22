<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Monitoring Application URL</title>
        <link rel="icon" href="/WEB-INF/images/web-development-logo-website-monitoring-png-favpng-yREngG4vnnTd89kP82pY8q87M.jpg" type="image/gif">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <style>
            body {
                color: #566787;
                background: #f5f5f5;
                font-family: 'Varela Round', sans-serif;
                font-size: 13px;
            }
            .table-responsive {
                margin: 30px 0;
            }

            .searchbar{
                margin-bottom: auto;
                margin-top: auto;
                height: 60px;
                background-color: #353b48;
                border-radius: 30px;
                padding: 10px;
            }

            .search_input{
                color: white;
                border: 0;
                outline: 0;
                background: none;
                width: 0;
                caret-color:transparent;
                line-height: 40px;
                transition: width 0.4s linear;
            }

            .searchbar:hover > .search_input{
                padding: 0 10px;
                width: 450px;
                caret-color:red;
                transition: width 0.4s linear;
            }

            .searchbar:hover > .search_icon{
                background: white;
                color: #e74c3c;
            }
            .but{
                margin: 0;
                position: absolute;
                top: 100%;
                left: 70%;
                -ms-transform: translate(-50%, -50%);
                transform: translate(-50%, -50%);
            }
            .search_icon{
                height: 40px;
                width: 40px;
                float: right;
                display: flex;
                justify-content: center;
                align-items: center;
                border-radius: 50%;
                color:white;
                text-decoration:none;
            }      

        </style>
    </head>
    <body>

        <%
            response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
        %>
        <%
            if (session.getAttribute("username") == null) {
                response.sendRedirect("Login.jsp");
            }
        %>


        <div class="jumbotron">
            <div class="container text-center">
                <h1 style="font-family: 'Varela Round', sans-serif;">Welcome to Monitoring Url Application</h1>      
            </div>
        </div>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>                        
                    </button>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">
                        <li><a href="#">View on Github</a></li>
                        <li><a href="#">Home</a></li>
                        <li><a href="Tablelist.jsp">URL List</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="logout"><span class="glyphicon glyphicon-log-out"></span> LogOut</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <br><br>

        <div class="container-fluid text-center">    
            <div class="row content">
                <div class="col-sm-8 text-left"> 

                    <div class="container">    
                        <div class="container h-100">
                            <div class="d-flex justify-content-center h-100">
                                <form action="monitor" method="post">
                                    <div class="form-group">
                                        <h2>Enter your site url below</h2><br>
                                        <input type="text" class="form-control" name="site_name" id="usr" required="required">
                                    </div>
                                    <br><br>
                                    <div class="form-group">        
                                        <div class="text-center">
                                            <button type="submit" class="btn btn-primary btn-md">Submit</button>
                                            <!--                                                <button type="submit" class="btn btn-success btn-lg btn-block">Submit</button>-->

                                        </div>
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br><br>
        <br>

        <footer class="container-fluid text-center">
            <p></p>
        </footer>

    </body>
</html>
