package ru.job4j.jaxb;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class JAXBExample {
    public static void main(String[] args) {

        Customer customer = new Customer();
        customer.setId(100);
        customer.setName("mkyong");
        customer.setAge(29);

        Customer customer1 = new Customer();
        customer1.setId(120);
        customer1.setName("fwwe");
        customer1.setAge(12);

        try {

            File file = new File("D:\\file.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

//            ArrayList<Customer> list = new ArrayList<>(
//                    Arrays.asList(
//                            customer, customer1
//                    )
//            )
            jaxbMarshaller.marshal(customer, file);
     //       jaxbMarshaller.marshal(customer, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
