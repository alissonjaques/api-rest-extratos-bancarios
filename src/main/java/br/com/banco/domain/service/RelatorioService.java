package br.com.banco.domain.service;

import br.com.banco.domain.utils.BibliotecaDeMetodos;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RelatorioService {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RelatorioService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public String gerarRelatorio(String nomeDoArquivo, List<String> parametros,
                               List<String> valores) throws IOException, JRException, SQLException {

        Map<String, Object> parameters = new HashMap<>();
        String nomeRelatorio = "src\\main\\java\\br\\com\\banco\\application\\Reports\\transferencia\\" + nomeDoArquivo;

        int i = 0;
        for (String parametro : parametros) {
            parameters.put(parametro, valores.get(i));
            i++;
        }

        JasperCompileManager.compileReportToFile(nomeRelatorio + ".jrxml");

        Connection connection = jdbcTemplate.getDataSource().getConnection();
        JasperPrint print = JasperFillManager.fillReport(nomeRelatorio + ".jasper", parameters, connection);
        connection.close();

        String caminhoArquivoSalvo = System.getProperty("java.io.tmpdir") + "\\" + nomeDoArquivo + BibliotecaDeMetodos.getAleatorio() + ".pdf";
        JasperExportManager.exportReportToPdfFile(print, caminhoArquivoSalvo);
        return caminhoArquivoSalvo;
    }
}