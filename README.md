# PruebaBQ
PRUEBA DE CANDIDATOS PARA ANDROID

El caso:

Hoy en día cualquier aplicación Android consume varias APIs, ya sean propias para dar soporte a la aplicación de lado del servidor como APIs de terceros..

En bq todos los ingenieros Android tienen una amplia experiencia consumiendo API's propias o de terceros, con el mayor foco posible en dar una experiencia al usuario inmejorable.

El caso que proponemos al candidato o candidata es el siguiente:
Desarrollar una aplicación Android que sea cliente de Evernote cumpliendo los siguientes puntos:

1. Exista una pantalla inicial de login, donde el usuario pueda introducir sus credenciales para tener acceso a su cuenta Evernote. (https://evernote.com/)

2. Una vez introducidos los credenciales, se mostrarán en pantalla todas las notas creadas por el usuario. (Modificado: Se muestran libretas, ya que Evenote no da permisos para consultar notas)

3. Dicha pantalla tendrá un menú desplegable con dos opciones, una de ellas ordenará la lista por el título de la libreta y la otra por fecha de creación o modificación.

4. Al hacer tap sobre una nota, se accederá al contenido de la misma. (No es necesario que las notas sean editables) (No es posible. Tampoco hay permisos para acceder a las notas dentro de una libreta)

5. Existirá un botón para “nueva nota” que permitirá crear una nota (con título y cuerpo) y posteriormente guardarla.
   Se ha añadido un botón "nueva libreta" que permitirá crear una libreta y posteriormente guardarla.


COMENTARIOS:

Al acceder con los credenciales de Github aparece una lista con las libretas del usuario. Aparecen libretas en lugar de notas ya que Evernote no da permisos para consultar notas, por lo tanto he realizado una lista con las libretas. 

Pinchando el menu superior, se puede seleccionar "ordenar por nombre" para ordenar alfabéticamente las libretas o "ordenar por fecha" para que aparezcan en la parte superior las libretas que se han actualizado recientemente.

Al presionar sobre el botón "Nueva nota" aparece una nueva ventana en la que se puede insertar el título de la nueva nota y el cuerpo. Posteriormente, mediante el botón "Crear nota" se guardará la nota y se volverá a la ventana anterior. No es posible visualizar la nueva nota creada ya que, como he comentado, Github no permite visualizar las notas del usuario, por lo que para consultar si la nueva se ha creado hay que acceder a https://sandbox.evernote.com/

Ya que el servicio no permite visualizar las nuevas notas creadas, se ha creado un nuevo botón para poder crear nuevas libretas. Una vez creada una nueva libreta aparecerá en la lista de libretas. 
