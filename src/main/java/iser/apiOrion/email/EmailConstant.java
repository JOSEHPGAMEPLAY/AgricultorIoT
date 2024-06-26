package iser.apiOrion.email;


public class EmailConstant {

    public static String formularioRegistrado(String nombre) {
        StringBuilder content = new StringBuilder();
        content.append("<!DOCTYPE html>")
                .append("<html lang='es'>")
                .append("<head>")
                .append("    <meta charset='UTF-8'>")
                .append("    <style>")
                .append("        main :is(p){")
                .append("            line-height: 0px;")
                .append("        }")
                .append("    </style>")
                .append("</head>")
                .append("<body>")
                .append("    <main>")
                .append("         <p> <strong>Estimado(a) Usuario</strong>, </p> ")
                .append("         <p> Se informa que se ha recibido un nuevo formulario para hacer parte de AgricultorIoT de <strong>")
                .append(            nombre )
                .append("           </strong> por favor revisa el formulario y decide si quieres que haga parte del proyecto. </p>")
                .append("         <br> ")
                .append("    </main>")
                .append("</body>")
                .append("</html>");
        return content.toString();
    }

}
