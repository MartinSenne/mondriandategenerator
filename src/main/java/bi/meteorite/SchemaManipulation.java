package bi.meteorite;

import java.util.Map;


/**
 * Created by bugg on 11/12/14.
 * Modified by Martin Senne 2015-12-01
 */
public class SchemaManipulation {

    public static UtilFrom4.Function1<String, String> insertPhysTable(
            final String tableDef) {
        return new UtilFrom4.Function1<String, String>() {
            public String apply(String schema) {
                int i = schema.indexOf("</PhysicalSchema>");
                return schema.substring(0, i)
                        + tableDef
                        + schema.substring(i);
            }
        };
    }

    public static UtilFrom4.Function1<String, String> insertDimension(
            final String cubeName, final String dimDefs) {
        return new UtilFrom4.Function1<String, String>() {
            public String apply(String schema) {
                int h = schema.indexOf("<Cube name='" + cubeName + "'");
                if (h < 0) {
                    throw new RuntimeException(
                            "cube '" + cubeName + "' not found");
                }
                int i = schema.indexOf("<Dimension ", h);
                return schema.substring(0, i)
                        + dimDefs
                        + schema.substring(i);
            }
        };
    }


    public static UtilFrom4.Function1<String, String> insertSharedDimension(
            final String sharedDimension) {
        return new UtilFrom4.Function1<String, String>() {
            public String apply(String schema) {
                int i = schema.indexOf("</PhysicalSchema>");
                return schema.substring(0, i + 17)
                        + sharedDimension
                        + schema.substring(i + 17);
            }
        };
    }

    public static UtilFrom4.Function1<String, String> insertDimensionLinks(
            final String cubeName, final Map<String, String> dimLinks) {
        return new UtilFrom4.Function1<String, String>() {
            public String apply(String schema) {
                int h = schema.indexOf("<Cube name='" + cubeName + "'");
                if (h < 0) {
                    throw new RuntimeException(
                            "cube '" + cubeName + "' not found");
                }
                int end = schema.indexOf("</Cube>", h);
                for (Map.Entry<String, String> entry : dimLinks.entrySet()) {
                    int i =  schema.indexOf(
                                    "<MeasureGroup name='" + entry.getKey() + "' ",
                                    h);
                    if (i < 0 || i > end) {
                        continue;
                    }
                    i = schema.indexOf("</DimensionLinks>", i);
                    if (i < 0 || i > end) {
                        continue;
                    }
                    schema = schema.substring(0, i)
                            + entry.getValue()
                            + schema.substring(i);
                }
                return schema;
            }
        };
    }
}
