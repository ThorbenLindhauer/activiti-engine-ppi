[simpleTimeProcess]: https://raw.github.com/ThorbenLindhauer/activiti-engine-ppi/master/doc/simpleTimeExample/process.png "A simple example process with PPIs"
[simpleTimeProcessXml]: https://raw.github.com/ThorbenLindhauer/activiti-engine-ppi/master/doc/simpleTimeExample/SimpleTimeMeasure.bpmn20.xml

Measuring Process Performance Indicators with Activiti
======================================================

This is a modified version of the Activiti open source BPM engine.
I have added support for the definition of Process Performance Indicators (PPIs) in a model-driven fashion as an outcome of a seminar work at university.

Links
=====

PPINOT Ontology: http://www.isa.us.es/ppinot/

Example Usage
=============

This is a Hello-World-like example process that measures a time duration in a process.
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

More documentation will be added in the future.