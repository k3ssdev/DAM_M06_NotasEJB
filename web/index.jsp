<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="utf-8">
        <title>EJB NotasLinkia</title>
        <link rel="stylesheet" href="style.css">
    </head>
<main>
    <body>
        <div class="container">
            <h1 id="titulo">Notas Linkia DAM</h1>
            <br>
            <form action="ServletLogin" method="POST">
                <h2>Login</h2>
                <br>
                <!-- <label>Introduce los datos de acceso:</label> -->
                <input type="text" name="username" value="" placeholder="Usuario..." />
                <input type="password" name="password" value="" placeholder="Contraseña..." />
                <select name="rol">
                    <option value="alumno">Alumno</option>
                    <option value="profesor">Profesor</option>
                </select>
                <br>
                <input type="submit" name="enviar" value="Enviar" />
            </form>
        </div>
    </body>
</main>
<footer>
    <p>2023 DAM M06 - Alberto Pérez</p>
</footer>

    </html>