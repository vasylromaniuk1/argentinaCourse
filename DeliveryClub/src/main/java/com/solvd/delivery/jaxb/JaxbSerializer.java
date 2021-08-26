package com.solvd.delivery.jaxb;

import static com.solvd.delivery.parsers.EmployeeParser.deSerializeToObject;
import static com.solvd.delivery.parsers.EmployeeParser.serializeToXML;

import com.solvd.delivery.DAO.classes.Employee;
import com.solvd.delivery.Main;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JaxbSerializer {

  public static final Logger LOGGER = LogManager.getLogger(JaxbSerializer.class);

  public static void serializeToXml (Employee employee) throws IOException {

    JAXBContext context = null;
    try {
      context = JAXBContext.newInstance(Employee.class);
      Marshaller m = context.createMarshaller();
      m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
      // Write to File
      m.marshal(employee, new File("jaxb.xml"));

    } catch (JAXBException e) {
      LOGGER.info(e.getLocalizedMessage());
    }

  }


  public static Employee deserialize (String fileName) throws IOException {
    try {
      JAXBContext context = JAXBContext.newInstance(Employee.class);
      Unmarshaller un = context.createUnmarshaller();
      Employee emp = (Employee) un.unmarshal(new File(fileName));
      return emp;
    } catch (JAXBException e) {
      LOGGER.info(e.getLocalizedMessage());
    }
    return null;

  }



}
