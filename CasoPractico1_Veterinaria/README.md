# EjercicioPráctico1 Veterinaria

Aplicación web para una clínica veterinaria desarrollada con Java 17, Spring Boot, Spring Web, Spring Data JPA, Thymeleaf, Bootstrap y MySQL.

## Requisitos
- JDK 17 o superior
- Maven (o utilizar el wrapper si se genera desde Spring Initializr)
- MySQL Server y MySQL Workbench
- Git

## 1. Base de datos
1. Abra MySQL Workbench.
2. Abra y ejecute `database/veterinaria_citas.sql`.
3. El script crea la base `veterinaria_citas`, usuario local y datos de prueba.
4. Si el usuario de MySQL ya existe o no tiene permiso para crear usuarios, ejecute el script por partes con una cuenta administradora y revise las credenciales.

> Advertencia: el script incluye `DROP DATABASE IF EXISTS veterinaria_citas`, lo cual borra la base anterior con ese nombre.

## 2. Credenciales
La configuración inicial está en `src/main/resources/application.properties`:
- Base de datos: `veterinaria_citas`
- Usuario: `usuario_caso_practico_01_var`
- Contraseña: `123456`
- Puerto web: `8086`

Si su MySQL usa otra contraseña, actualice `spring.datasource.username` y `spring.datasource.password` según su configuración local.

## 3. Ejecutar
Desde la carpeta del proyecto:

```bash
mvn spring-boot:run
```

Abra `http://localhost:8086`.

## 4. Rutas
- `/` página de inicio y formulario de consultas
- `/tipos` CRUD de tipos de servicio
- `/servicios` CRUD de servicios veterinarios
- `/citas` consulta de solo lectura

## 5. GitHub
1. Cree en GitHub un repositorio público o privado llamado `EjercicioPractico1_Veterinaria_TUNOMBRE` (reemplace TUNOMBRE por su nombre, según las instrucciones del curso).
2. No agregue README, licencia ni `.gitignore` desde GitHub si ya vienen en este proyecto.
3. Abra una terminal en la carpeta raíz del proyecto y ejecute:

```bash
git init
git add .
git commit -m "Primer avance: aplicación web veterinaria"
git branch -M main
git remote add origin https://github.com/TU-USUARIO/EjercicioPractico1_Veterinaria_TUNOMBRE.git
git push -u origin main
```

Reemplace `TU-USUARIO` por su usuario de GitHub y ajuste el URL al repositorio real. GitHub puede pedir iniciar sesión o autorizar Git Credential Manager. Nunca comparta aquí su contraseña o token.

## Organización inspirada en el proyecto de referencia
- `controller`: controladores web.
- `domain`: entidades JPA y asociaciones.
- `repository`: interfaces de acceso a datos.
- `service` e `service/impl`: lógica de negocio separada.
- `templates/general/fragmentos.html`: fragmentos reutilizados para `head`, `header`, `navbar` y `footer`, siguiendo el patrón de `th:replace` usado en el proyecto de referencia.
- `templates/tipos`, `templates/servicios` y `templates/citas`: páginas separadas por módulo.

Se mantiene código Java explícito con constructores/getters/setters, sin depender de Lombok ni Firebase, para que las clases sean fáciles de seguir y la solución respete la exigencia de MySQL local.

## Funcionalidades incluidas
- CRUD de tipos de servicio con cantidad de servicios asociados.
- CRUD de servicios con selección de tipo.
- Listado de citas en modo lectura y fecha `dd/MM/yyyy`.
- Modales de confirmación para eliminar.
- Mensajes de resultado con `RedirectAttributes`.
- Página promocional, formulario de contacto de interfaz (no persiste datos).
- Fragmentos compartidos de header, navbar y footer.
- Textos comunes desde `messages.properties`.

## Nota de entrega
Pruebe el proyecto en su computadora y asegúrese de poder explicar sus clases y decisiones técnicas antes de entregarlo. Revise también los datos de conexión y la ejecución de las consultas en MySQL.
