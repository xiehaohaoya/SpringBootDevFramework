package cn.xhh.dev.controller;

import cn.xhh.dev.common.annotation.DataSource;
import cn.xhh.dev.common.enums.DataSourcesType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Controller
public class JdbcTemplateController {

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @ResponseBody
    @RequestMapping("queryUserForList")
    @DataSource(name = DataSourcesType.SLAVE)
    public List<Map<String, Object>> getData() {
        String sql = "select * from user";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }
}
