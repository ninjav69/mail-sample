package coza.dcmgroup.dao;

import coza.dcmgroup.entity.*;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by richard.solomon on 2014-05-29.
 */
public class LookupDAO {

    // mark
    public static SystemVariable getSystemVariable(String name) {
        SystemVariable sv = new SystemVariable();
        sv.setId(0);
        sv.setName(name);
        sv.setValue("carepremiermails@dcmgroup.co.za");
        return sv;
    }
}
