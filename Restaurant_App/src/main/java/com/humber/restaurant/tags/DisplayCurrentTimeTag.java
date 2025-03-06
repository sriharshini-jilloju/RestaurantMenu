package com.humber.restaurant.tags;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DisplayCurrentTimeTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            // Get current date and time
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(new Date());
            
            // Write the current time to the page
            out.print("<p>Current Time: " + currentTime + "</p>");
            
            // Optionally, you can add copyright information as well
            out.print("<p>&copy; 2025 The Velvet Fork</p>");
        } catch (Exception e) {
            throw new JspException("Error in DisplayCurrentTimeTag", e);
        }
        return SKIP_BODY; // Skip the body content as the time is rendered here
    }
}
