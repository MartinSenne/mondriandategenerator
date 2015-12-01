# Mondrian DateTable Generator

A DynamicSchemaProcessor (DSP) that creates a dynamic "in query" date dimension  as `<inlinetable>` in your schema.

## Usage

Use a connection string like

<div style="font-family: 'Courier New', monospace;">
jdbc:mondrian:Jdbc=jdbc:mysql://localhost/foodmart;Catalog=mondrian:///datasources/foodmart4.xml;
JdbcDrivers=com.mysql.jdbc.Driver;
<b>DynamicSchemaProcessor=bi.meteorite.App;
StartDate=19970101;
EndDate=19981231;
Cubes=Sales=the_date,Warehouse=time_id</b>
</div>

<p></p>

### Parameters

* **StartDate** (Mandatory)
* **EndDate** (Mandatory)
* **Cubes** (Mandatory) String Mandatory to define the size of the dimension.
* **InitId**: (Optional): If you have an incremental id field you'd rather link to (which is much more performant) use InitID parameter to pass a starting integer.


This is supposed to make Proof Of Concepts and Demo's much quicker to get off the ground, it is not supposed to provide the same level of performance that a table in a database can provide and joining 2 date fields is pretty rubbish.

Tested on MySQL, log bugs at http://jira.meteorite.bi
