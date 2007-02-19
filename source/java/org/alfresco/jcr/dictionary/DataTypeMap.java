/*
 * Copyright (C) 2005-2007 Alfresco Software Limited.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.

 * As a special exception to the terms and conditions of version 2.0 of 
 * the GPL, you may redistribute this Program in connection with Free/Libre 
 * and Open Source Software ("FLOSS") applications as described in Alfresco's 
 * FLOSS exception.  You should have recieved a copy of the text describing 
 * the FLOSS exception, and it is also available here: 
 * http://www.alfresco.com/legal/licensing"
 */
package org.alfresco.jcr.dictionary;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.PropertyType;
import javax.jcr.RepositoryException;

import org.alfresco.error.AlfrescoRuntimeException;
import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.namespace.QName;


/**
 * Responsible for mapping Alfresco Data Types to JCR Property Types and vice versa.
 * 
 * @author David Caruana
 */
public class DataTypeMap
{

    /** Map of Alfresco Data Type to JCR Property Type */
    private static Map<QName, Integer> dataTypeToPropertyType = new HashMap<QName, Integer>();
    static
    {
        dataTypeToPropertyType.put(DataTypeDefinition.TEXT, PropertyType.STRING);
        dataTypeToPropertyType.put(DataTypeDefinition.MLTEXT, PropertyType.STRING);
        dataTypeToPropertyType.put(DataTypeDefinition.CONTENT, PropertyType.BINARY);
        dataTypeToPropertyType.put(DataTypeDefinition.INT, PropertyType.LONG);
        dataTypeToPropertyType.put(DataTypeDefinition.LONG, PropertyType.LONG);
        dataTypeToPropertyType.put(DataTypeDefinition.FLOAT, PropertyType.DOUBLE);
        dataTypeToPropertyType.put(DataTypeDefinition.DOUBLE, PropertyType.DOUBLE);
        dataTypeToPropertyType.put(DataTypeDefinition.DATE, PropertyType.DATE);
        dataTypeToPropertyType.put(DataTypeDefinition.DATETIME, PropertyType.DATE);
        dataTypeToPropertyType.put(DataTypeDefinition.BOOLEAN, PropertyType.BOOLEAN);
        dataTypeToPropertyType.put(DataTypeDefinition.QNAME, PropertyType.NAME);
        dataTypeToPropertyType.put(DataTypeDefinition.CATEGORY, PropertyType.STRING);  // TODO: Check this mapping
        dataTypeToPropertyType.put(DataTypeDefinition.NODE_REF, PropertyType.REFERENCE);
        dataTypeToPropertyType.put(DataTypeDefinition.PATH, PropertyType.PATH);
        dataTypeToPropertyType.put(DataTypeDefinition.ANY, PropertyType.UNDEFINED);
    }
    
    /** Map of JCR Property Type to Alfresco Data Type */
    private static Map<Integer, QName> propertyTypeToDataType = new HashMap<Integer, QName>();
    static
    {
        propertyTypeToDataType.put(PropertyType.STRING, DataTypeDefinition.TEXT);
        propertyTypeToDataType.put(PropertyType.BINARY, DataTypeDefinition.CONTENT);
        propertyTypeToDataType.put(PropertyType.LONG, DataTypeDefinition.LONG);
        propertyTypeToDataType.put(PropertyType.DOUBLE, DataTypeDefinition.DOUBLE);
        propertyTypeToDataType.put(PropertyType.DATE, DataTypeDefinition.DATETIME);
        propertyTypeToDataType.put(PropertyType.BOOLEAN, DataTypeDefinition.BOOLEAN);
        propertyTypeToDataType.put(PropertyType.NAME, DataTypeDefinition.QNAME);
        propertyTypeToDataType.put(PropertyType.REFERENCE, DataTypeDefinition.NODE_REF);
        propertyTypeToDataType.put(PropertyType.PATH, DataTypeDefinition.PATH);
        propertyTypeToDataType.put(PropertyType.UNDEFINED, DataTypeDefinition.ANY);
    }
    
    /**
     * Convert an Alfresco Data Type to a JCR Property Type
     * 
     * @param datatype  alfresco data type
     * @return  JCR property type
     * @throws RepositoryException
     */
    public static int convertDataTypeToPropertyType(QName datatype)
    {
        Integer propertyType = dataTypeToPropertyType.get(datatype);
        if (propertyType == null)
        {
            throw new AlfrescoRuntimeException("Cannot map Alfresco data type " + datatype + " to JCR property type.");
        }
        return propertyType;
    }

    /**
     * Convert a JCR Property Type to an Alfresco Data Type
     * 
     * @param  propertyType  JCR property type
     * @return  alfresco data type
     * @throws RepositoryException
     */
    public static QName convertPropertyTypeToDataType(int propertyType)
    {
        QName type = propertyTypeToDataType.get(propertyType);
        if (type == null)
        {
            throw new AlfrescoRuntimeException("Cannot map JCR property type " + propertyType + " to Alfresco data type.");
        }
        return type;
    }

}
