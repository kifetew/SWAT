/*
 * Copyright (C) 2005-2006 Jabref-Team
 * 
 * All programs in this directory and subdirectories are published under the GNU
 * General Public License as described below.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * Further information about the GNU GPL is available at:
 * http://www.gnu.org/copyleft/gpl.ja.html
 *
 */
package net.sf.jabref.export.layout.format;

import net.sf.jabref.export.layout.LayoutFormatter;
import net.sf.jabref.AuthorList;

/**
 * <ul>
 * <li>Names are given in order: von last, jr, first.</li>
 * <li>First names will NOT be abbreviated.</li>
 * <li>Individual authors are separated by commas.</li>
 * <li>There is no comma before the 'and' at the end of a list of three or more authors</li>
 * </ul>
 * 
 * @author mkovtun
 * @author Christopher Oezbek <oezi@oezi.de>
 * 
 */
public class AuthorLastFirstCommas implements LayoutFormatter {

    public String format(String fieldText) {
        return AuthorList.fixAuthor_lastNameFirstCommas(fieldText, false, false);
    }
}