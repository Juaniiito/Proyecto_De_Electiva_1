# Plantilla de proyecto

Este repositorio contiene una plantilla de proyecto que puede usar para inicializar su propio repositorio. Esta plantilla incluye:

- Estructura de carpetas con la documentación
- Integración con Github Actions:
  - Integración continua
  - Pruebas automatizadas usando Firebase Test Lab

Para usar esta plantilla:

1. Cree su propio repositorio [usando este como plantilla](https://docs.github.com/es/repositories/creating-and-managing-repositories/creating-a-repository-from-a-template)
2. Clone el repositorio localmente
3. Puede iniciar modificando el archivo README para incluir los detalles de su proyecto
4. Cuando cree su proyecto desde Android Studio **asegúrese de seleccionar la carpeta donde clono este repositorio como carpeta para su proyecto**

Una vez suba el código de su proyecto de Android el workflow de integración continua se ejecutara de forma automática. Puede ver el resultado junto a su ultimo commit con un icono de éxito :check o de fallo :failure. Dando clic allí podrá ver mas detalles.

## Integración continua

Este proyecto ya tiene habilitados dos workflows de integración continua para compilar y probar de forma automática su aplicación:

1. [android.yml](.github/workflows/android.yml): este flujo de trabajo compila su app, guarda la version del APK generado, y lo envía a Firebase Test Lab para que se pruebe de forma automática. 

1. [unity.yml](.github/workflows/unity.yml): este flujo de trabajo compila una app generada con Unity, guarda la version del APK generado, y lo envía a FIrebase Test Lab para que se pruebe de forma automática. Este flujo de trabajo esta comentado por defecto. Si va a construir una aplicación con Unity deberá ejecutar este workflow manualmente desde el menu de Actions en su repositorio, y/o habilitar este workflow y deshabilitar el de Android Studio comentando/descomentando las siguientes lineas al inicio de los archivo YAML de cada workflow (para comentar use el símbolo `#`):

   ```yaml
   push:
     branches: [ "main" ]
   pull_request:
     branches: [ "main" ]
   ```

   Este flujo requiere que se configuren tres secrets en su repositorio para funcionar. Estos secrets tienen la información de su licencia de Unity para poder compilar el proyecto. Esta información es privada.

   - UNITY_LICENSE: documento JSON con la información de su licencia
   - UNITY_EMAIL: el correo con el que se ha registrado en Unity
   - UNITY_PASSWORD: su contraseña de acceso a Unity

   Puede encontrar mas información sobre como configurar estas variables en este [link](https://game.ci/docs/github/activation).

## Firebase Test Lab

Puede ejecutar la aplicación instalando el APK generado en Test Lab de Firebase de manera que se asegure de que la aplicación además de generarse correctamente, tiene un funcionamiento básico correcto (sanity test). **Por defecto esta tarea fallara si no esta configurada**.

Para configurar esta tarea siga los siguientes pasos:

1. Cree un nuevo proyecto en [Firebase Test Lab](https://console.firebase.google.com/)
1. Generar una nueva llave privada desde Firebase desde el menú `Project Overview -> Project Settings -> Service Accounts`
1. Crear un secret llamado `GCP_FIREBASE_CREDENTIALS` en su repositorio de Github en el menú `Settings -> Secrets and Variables -> Actions`
1. Asignar el rol de `Project -> Editor` a su cuenta de servicio de firebase (e.g. firebase-adminsdk-nzppq@myproject-12ac0.iam.gserviceaccount.com) en la [página principal de IAM](https://console.cloud.google.com/iam-admin/iam)
1. Habilitar el API Cloud Tool Results para el proyecto desde la [consola de Google Cloud](https://console.cloud.google.com/apis/api/toolresults.googleapis.com/)

Luego de hacer esto la tarea de Firebase Test Lab disponible en cualquier de los dos workflows comenzara a funcionar.
