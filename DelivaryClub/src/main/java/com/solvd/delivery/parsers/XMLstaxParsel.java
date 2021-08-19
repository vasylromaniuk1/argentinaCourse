package com.solvd.delivery.parsers;


import com.solvd.delivery.DAO.classes.Employee;
import java.io.InputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class XMLstaxParsel {
    public static final Logger LOGGER = LogManager.getLogger(XMLstaxParsel.class);

    public static void main(String[] args) {
        String fileName = "employees.xml";
        List<Employee> employeeList = parseXMLfile(fileName);
        for (Employee employee : employeeList) {
            LOGGER.info(employee.toString());
        }
    }

    public static List<Employee> parseXMLfile(String fileName) {

        List<Employee> employeesList = new ArrayList<>();
        Employee employee = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

        try {
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
            while (reader.hasNext()) {
                XMLEvent xmlEvent = reader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    String localPart = startElement.getName().getLocalPart();
                    if (localPart.equals("employee")) {
                        employee = new Employee();
                        Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                        if (idAttr != null) {
                            employee.setId(Long.valueOf(idAttr.getValue()));
                        }
                    } else if (localPart.equals("fname")) {
                        xmlEvent = reader.nextEvent();
                        employee.setName(xmlEvent.asCharacters().getData());
                    } else if (localPart.equals("lname")) {
                        xmlEvent = reader.nextEvent();
                        employee.setLastName(xmlEvent.asCharacters().getData());
                    }
                    else if (localPart.equals("age")) {
                        xmlEvent = reader.nextEvent();
                        employee.setAge(Integer.valueOf(xmlEvent.asCharacters().getData()));
                    }

                }


                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("employee")) {
                        employeesList.add(employee);
                    }
                }
            }

        } catch (XMLStreamException | FileNotFoundException exc) {
            LOGGER.error( exc.getLocalizedMessage());
        }
        return employeesList;
    }
}
