<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="utf-8">
        <title>EJB NotasLinkia</title>
        <link rel="stylesheet" href="style.css">
    </head>

    <body>
        <div class="container">
            <h1>Notas Linkia DAM</h1>
            <form action="ServletLogin" method="POST">
                <h2>Login</h2>
                <label>Introduce los datos de acceso:</label>
                <input type="text" name="username" value="" placeholder="Usuario..." />
                <input type="password" name="password" value="" placeholder="Contraseña..." />
                <select name="rol">
                    <option value="alumno">Alumno</option>
                    <option value="profesor">Profesor</option>
                    <option value="admin">Admin</option>
                </select>
                <input type="submit" name="enviar" value="Enviar" />
            </form>
        </div>
    </body>

    </html>