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
package org.alfresco.repo.workflow.jbpm;

import org.dom4j.Element;
import org.jbpm.graph.def.Action;
import org.jbpm.graph.def.Event;
import org.jbpm.instantiation.Delegation;
import org.jbpm.jpdl.xml.JpdlXmlReader;


/**
 * Implementation of Join which ends child tokens / tasks for nOutM cases.
 * 
 * @author davidc
 *
 */
public class Join extends org.jbpm.graph.node.Join
{
    private static final long serialVersionUID = 6417483503439714897L;

    /**
     * Constructor
     */
    public Join()
    {
    }

    /**
     * Constructor
     */
    public Join(String name)
    {
        super(name);
    }

    /* (non-Javadoc)
     * @see org.jbpm.jpdl.xml.Parsable#read(org.dom4j.Element, org.jbpm.jpdl.xml.JpdlXmlReader)
     */
    public void read(Element element, JpdlXmlReader jpdlReader)
    {
        // Add "on node leave" event handler which ends child tokens / tasks
        Delegation delegation = new Delegation(JoinEndForkedTokens.class.getName());
        Action action = new Action(delegation);
        Event event = new Event(Event.EVENTTYPE_NODE_LEAVE);
        event.addAction(action);
        addEvent(event);
    }

}
