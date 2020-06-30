package checker;
import javax.xml.namespace.QName;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import java.util.Objects;

public class ElementChecker {
    public static boolean isElementWith(StartElement element, String elementName) {
        String name = element.getName().getLocalPart();

        return elementName.equals(name);
    }

    public static boolean isElementWith(StartElement element, String elementName, String attributeName,
            String attributeValue) {
        String name = element.getName().getLocalPart();

        if (!elementName.equals(name)) {
            return false;
        }

        Attribute attribute = element.getAttributeByName(new QName(attributeName));

        return Objects.isNull(attribute) ? false : attributeValue.equals(attribute.getValue());
    }

    public static boolean isElementWith(EndElement endElement, String elementName) {
        String name = endElement.getName().getLocalPart();

        return elementName.equals(name);
    }
}