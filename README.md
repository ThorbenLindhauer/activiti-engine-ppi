[simpleTimeProcess]: https://raw.github.com/ThorbenLindhauer/activiti-engine-ppi/master/doc/simpleTimeExample/process.png "A simple example process with PPIs"
[simpleTimeProcessXml]: https://raw.github.com/ThorbenLindhauer/activiti-engine-ppi/master/doc/simpleTimeExample/SimpleTimeMeasure.bpmn20.xml
[ppinotHome]: http://www.isa.us.es/ppinot/
[ppinotEditor]: http://www.isa.us.es/ppinot/?PPINOT_Tool_Suite:PPINOT_Graphical_Editor
[ppinotLiterature]: http://www.isa.us.es/user/24/publications
[linksSection]: https://github.com/ThorbenLindhauer/activiti-engine-ppi#links
[activitiPPIexample]: https://github.com/ThorbenLindhauer/activiti-engine-ppi-example

Measuring Process Performance Indicators with Activiti
======================================================

This is a modified version of the Activiti open source BPM engine.
I have added support for the definition of Process Performance Indicators (PPIs) in a model-driven fashion as an outcome of a seminar work at university.
The prototype allows you to measure time durations between activities (Time Measure), the times a certain activity is executed (Count Measure), 
properties of data objects (Data Measure) and aggregate these values over all process instances applying aggregation functions like sum, min, max and average (Aggregation Measure).
You may also derive new values from these aggregated ones (Dervied Process Measure).

This prototype is based on an ontology and graphical notation that has been created and published by a team of researchers from the University of Sevilla. 
You find resources on the ontology and the types of measures it includes, as well as an editor that is based on Oryx to create BPMN models with PPI elements in the [Links][linksSection] section.

There exists a [sample application][activitiPPIexample] that demonstrates the capabilities of this extension.

Links
=====

* [PPINOT Ontology][ppinotHome]
* [Graphical editor][ppinotEditor]
* The publication that introduces the PPI ontology: Adela Del-Río-Ortega, Manuel Resinas, and Antonio Ruiz-Cortés. *Defining process performance indicators: an ontological approach.* In Proceedings of the 2010 inter-national conference on On the move to meaningful internet systems - Volume Part I, OTM'10, pages 555-572, Berlin, Heidelberg, 2010. Springer-Verlag. [PDF][ppinotLiterature]

Example Usage
=============

To benefit from the added features, you need a process model that contains PPI elements, such as this Hello-World-like example process that measures a time duration in a process.

![alt text][simpleTimeProcess]

The time between the beginning of activity "A" and the end of activity "B" is concerned and is supposed to be less than seven seconds.

You can find the full BPMN 2.0 XML [here][simpleTimeProcessXml].

The PPI-relevant elements are added in an extension element:

```xml
<extensionElements>
	<ppinot:ppiset>
		...
	</ppinot:ppiset>
</extensionElements>
```

The basic elements are the measures. In this case, the graphical element that represents a Time Measure
as well as its aggregation, is a nested element:
```xml
<ppinot:aggregatedMeasure analysisperiod=""
	samplingfrequency="" aggregationfunction="Average"
	id="aggMeasure">
	<ppinot:timeMeasure id="oryx_time1"
		unitofmeasure="" scale="" description="" name="" />
</ppinot:aggregatedMeasure>
```

The measure is connected to process elements, in our case the activities "A" and "B" via TimeConnector elements:
```xml
<ppinot:TimeConnector conditiontype="From"
	counatend="Start" targetRef="serviceTask"
	sourceRef="oryx_time1" id="timeConnector1" />
<ppinot:TimeConnector conditiontype="To"
	counatend="End" targetRef="serviceTask2"
	sourceRef="oryx_time1" id="timeConnector2" />
```

At this point this implementation diverges from the XML the editor exports, as the exported XML attaches
the Time Connectors to the aggregated measure. From my point of view it is better to attach the connectors to the Time Measure itself.

The top level element is the PPI element. It references the measure the PPI is defined on and defines a target value.
```xml
<ppinot:ppi id="my_ppi"
measuredBy="aggMeasure" target="&lt; 7000" />
```

You can run the process just like any other Activiti process. For PPI support you have to set the process engine configuration class in your activiti.cfg as follows:
```xml
<bean id="processEngineConfiguration" class="de.unipotsdam.hpi.thorben.ppi.engine.PPIStandaloneProcessEngineConfiguration">
```

The measures values are then written to the Activiti database. Supported databases are H2 and MySQL so far.

**Note**: There is no schema upgrade defined for the PPI tables and I recommend to use this prototype with a clean database.

You can then use the PPIService to request PPI values and check, whether a PPI is currently fulfilled:

```java
PPIProcessEngine engine = (PPIProcessEngine) processEngine;
PPIService ppiService = engine.getPPIService();
Number result = ppiService.calculatePPI("my_ppi", "simpleTimeMeasure");
boolean fulfilled = ppiService.PPIfulfilled("my_ppi", "simpleTimeMeasure");
```

Implementation-specific details
===============================

* Data Measures are measured, if the ExecutionEntity#setVariable method is called. If you simply modify a data object in Java without updating by calling this method, this change is not tracked. Something like a dynamic proxy object could be used to solve this.
* Functions for derived measures are to be expressed using JUEL
* As mentioned above, the editor exports the connectors attached to the aggregated measures and not to the base (time, count, data) measures. The parser expects them to be connected to the base measures. This has to be corrected manually in the XML.

Limitations
===========
* Condition Measures are not part of the prototype yet. These measures represent a condition in a process, for example if an activity has been executed.
* PPI and Measure calculations are not time-bounded yet, which means that for the calculation of a certain measure/PPI all existing values are regarded.
* As it is a prototype I have so far only looked at correctly defined and meaningful measures. Meaningless measures like for example a time measure with an end trigger that is located in the process before the start trigger may lead to runtime errors.

Not complete yet.