/*
Copyright (C) 2009, 2013  Red Hat

This file is part of IcedTea.

IcedTea is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2, or (at your option)
any later version.

IcedTea is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License
along with IcedTea; see the file COPYING.  If not, write to the
Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
02110-1301 USA.

Linking this library statically or dynamically with other modules is
making a combined work based on this library.  Thus, the terms and
conditions of the GNU General Public License cover the whole
combination.

As a special exception, the copyright holders of this library give you
permission to link this library with independent modules to produce an
executable, regardless of the license terms of these independent
modules, and to copy and distribute the resulting executable under
terms of your choice, provided that you also meet, for each linked
independent module, the terms and conditions of the license of that
module.  An independent module is a module which is not derived from
or based on this library.  If you modify this library, you may extend
this exception to your version of the library, but you are not
obligated to do so.  If you do not wish to do so, delete this
exception statement from your version. *
 */
package net.sourceforge.jnlp.util.logging.headers;

import net.sourceforge.jnlp.util.logging.OutputControllerLevel;

import java.util.Date;
import java.util.regex.Pattern;

import static net.sourceforge.jnlp.util.logging.OutputControllerLevel.WARNING_ALL;

public class PluginHeader extends Header {

    static final String PLUGIN_DEBUG = "plugindebug ";
    static final String PLUGIN_DEBUG_PREINIT = "preinit_plugindebug ";
    static final String PLUGIN_ERROR = "pluginerror ";
    static final String PLUGIN_ERROR_PREINIT = "preinit_pluginerror ";
    static final Pattern bracketsPattern = Pattern.compile("(\\]\\s*\\[)|(\\s*\\[)|(\\]\\s*)");
    static final Pattern whiteSpaces = Pattern.compile("\\s+");
    static final Pattern threadsPattern = Pattern.compile("\\s+|,\\s*|:");

    public final boolean preInit;

    PluginHeader() {
        this(WARNING_ALL, new Date(), new Date().toString(), default_user, unknown, unknown, unknown, false);
    }

    PluginHeader(OutputControllerLevel level, Date timestamp, String date, String user, String caller, String thread1, String thread2, boolean preInit) {
        super(level, timestamp, date, false, true, false, user, caller, thread1, thread2);
        this.preInit = preInit;
    }

    @Override
    public String toString() {
        return toString(true, true, true, true, true, true, true);
    }
      
    @Override
    public String toString(boolean userb, boolean originb, boolean levelb, boolean dateb, boolean callerb, boolean thread1b, boolean thread2b) {
        final String result = super.toString(userb, originb, levelb, dateb, callerb, thread1b, thread2b);

        if (preInit) {
            return "!" + result;
        } else {
            return result;
        }
    }

    @Override
    public String thread1ToString() {
        return " ITNPP Thread# " + thread1;
    }

    @Override
    public String thread2ToString() {
        return "gthread " + thread2;
    }
}
