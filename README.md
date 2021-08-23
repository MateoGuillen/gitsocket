# TP-SOCKETS

<span style="text-decoration: underline">**Trabajo Práctico**</span>: Sockets TCP/UDP

<span style="text-decoration: underline">**Grupos**</span>:
* Estudiantes con apellidos que inician con letra “A” hasta “H”
* De 3 a 5 integrantes
* Integrantes:
    * Mateo Giovanni Guillen Vera
    * Martin Alejandro Aponte Cabriza
    * Camila Alderete González
    * Matías Leonardo González Espínola

<span style="text-decoration: underline">**Objetivo principal**</span>: Realizar el diseño y desarrollo de sistemas distribuidos utilizando comunicación entre procesos sobre protocolos TCP y UDP y comprender las dificultades de su implementación.

<span style="text-decoration: underline">**Objetivos específicos:**</span>
* Enfrentarse a la problemática de diseñar sistemas que provean servicios e interfaces a otros sistemas.
* Comprender los desafíos en el proceso de desarrollo de integración de sistemas. 
* Desarrollar e implementar sistemas basados en envío y recepción de paquetes utilizando protocolo UDP.
* Desarrollar e implementar sistemas utilizando flujos de comunicación utilizando protocolo TCP.

<span style="text-decoration: underline">**Especificación del sistema distribuido**</span>
El objetivo del trabajo es contar con aplicaciones distribuidas geográficamente basadas en Sockets TCP/UDP o WebSocket que permitan operar eficientemente a las organizaciones involucradas.

![](/img/grafico.svg)


<span style="text-decoration: underline">**Proyectos a desarrollar e implementar**</span>

Cada grupo deberá crear y desarrollar los siguientes proyectos:
* Un proyecto para el Sistema de Servicios (Servidor Central) de hospitales
    * Debe ofrecer servicios sobre TCP o UDP.
    * Debe contar con la información de camas disponibles, no disponibles y ocupación.
    * Debe contar con una base de datos en memoria (variables globales) o persistente de las camas de UTI por cada hospital.
    * La estructura debe soportar almacenar datos como lo siguiente:
        * HospitalX1 - Cama1 : ocupada
        * HospitalX1 - Cama2 : desocupada
    * Operaciones que debe soportar (tipo_operacion)
        * Ver el estado actual de todos los hospitales.
        * Crear Cama UTI
        * Eliminar Cama UTI 
        * Ocupar Cama UTI
        * Desocupar Cama UTI
    * Adicionalmente el servidor deberá guardar un registro de las operaciones realizadas en un log.
        * El log puede ser un archivo, una estructura almacenada en memoria ó una tabla en base de datos.
        * Debe contener: fecha-hora, origen (ip:puerto), destino (ip:puerto), tipo_operacion.
* Un proyecto <span style="text-decoration: underline">**Cliente**</span> que será cada hospital
    * Debe ser una aplicación cliente consola (standalone) o con interfaz gráfica que realice las operaciones ofrecidas por el servidor
    * Debe implementar los servicios del Servidor Central.


<span style="text-decoration: underline">**Representación de Datos**</span>

Para la representación de datos debe utilizarse formato/notación JSON.

Cada interacción entre los intervinientes debe contener mínimamente los siguientes atributos:
* estado, donde:
    * "0" corresponde a una transacción exitosa.
    * "-1" transacción indeterminada.
    * Mayor a "1" un código de error o mensaje relacionado a la transacción.
* mensaje
    * Palabra “ok” si no existe error.
    * El detalle el error si existe.
* tipo_operacion:
    * 1: ver_estado
    * 2: crear_cama
    * 3: eliminar_cama
    * 4: ocupar_cama
    * 5: desocupar_cama
    * Otros a considerar
* Cuerpo u otros datos específicos según el tipo de operación.

<span style="text-decoration: underline">**Criterios a tener en cuenta para la calificación. Lista enunciativa y no limitativa:**</span>

* Cumplir con la especificación solicitada.
* Desarrollar aplicaciones clientes y servidores necesarios para el cumplimiento del sistema solicitado.
* Cada componente del sistema distribuido debe tener su propio proyecto de software, es decir: cada componente/sistema debe estar en carpetas separadas sin que estén relacionadas entre sí a nivel de código fuente. Esto a fin de cumplir con la independencia y autonomía de cada sistema.
* En relación al repositorio deben tener un solo proyecto GIT en plataforma gitlab.com o github.com. El repositorio debe ser visible para el usuario: fmancia. Dentro del repositorio debe haber una carpeta diferente para cada proyecto.
* Definir la estructura de datos que cada fuente de datos debe manejar. Utilizar JSON como representación de datos para el intercambio.
* Diseñar el mecanismo de prueba sencillo y simple para la demostración frente al profesor y audiencia.
* Diseñar una interfaz en el cliente acorde a las pruebas.
* Crear una nomenclatura y jerarquía ordenada para cada archivo de código fuente que necesiten. Será valorada dicha organización.
* Dentro del repositorio del proyecto debe contener un archivo "README.md" donde se especifique:
    * Los nombres de los alumnos que realizaron el trabajo.
    * Requerimientos de instalación.
    * Cómo crear la estructura de Base de datos.
    * Cómo poblar los datos iniciales necesarios de Base de datos.
    * Cómo compilar y ejecutar los componentes de cada servidor.
    * Cómo compilar y ejecutar el/los clientes.
    * Documentación de un API de servicios ofrecidos por el Servidor.
    * Especificar la forma de invocación y parámetros de cada servicio ofrecido por el servidor.
    * El API documentado debe ser leído e implementado por otros grupos de desarrolladores.
* No se permiten plagios.


Forma de entrega: Indicaciones en: https://www.educa.una.py/politecnica/mod/assign/view.php?id=274178 