/**
 * Essa classe tem a responsabilidade de implementar uma interface gráfica de
 * contato com o usuário solicitando os dados para conexao com o banco de dados
 * e o caminho do arquivo responsavel por gerar o comprovante de locação
 */
package com.parking.telas;

import com.parking.files.ConfiguracaoArquivo;
import com.parking.files.FileRead;
import com.parking.files.PrintLogger;
import com.parking.files.entity.Impressoes;
import com.parking.frame.TelaPrincipal;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Diovani Bernardi
 */
public class TelaConfiguracao extends javax.swing.JDialog {

    //Objeto responsavel por imprimir em arquivo as eventuais excessões geradas
    private PrintLogger logger = new PrintLogger();
    //Objeto responsavel por abrir o selecionador de arquivos
    private JFileChooser explorer;
    //Objeto responsavel por armazenar uma instancia da tela principal do software
    private TelaPrincipal telaPrincipal;
    //Objeto responsavel por carregar os dados do arquivos de configuração
    private FileRead fileRead;
    //Objeto responsavel por armazenar as configurações para a conexao com o banco de dados
    private ConfiguracaoArquivo configuracaoArquivo;
    //Objeto responsavel por armazenar as configurações do caminho para o relatório
    private Impressoes impressoes;

    public TelaConfiguracao(java.awt.Frame parent, boolean modal, TelaPrincipal telaPrincipal) {
        super(parent, modal);
        try {
            initComponents();
            //inicialização do objeto recebido por parametro
            this.telaPrincipal = telaPrincipal;
            //chamada ao metodo responsavel por carregar os dados dados para o formulario
            this.carregarDadosArquivo();
            //desabilito o botao salvar
            this.botoesEditaveis(false);
            //desabilito os campos do formulario
            this.habilitarCampos(false);
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaConfiguracao.class, "public TelaConfiguracao()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        }
    }

    /**
     * metodo responsavel por carregar os dados do arquivo de configuração e
     * seta-los dentro do formulario de cadastro
     *
     */
    private void carregarDadosArquivo() {
        try {
            //inicialização do arquivo responsavel por ler os dados do arquivo de configuração
            this.fileRead = new FileRead();
            //inicializacao do objeto do configuracaoarquivo
            this.configuracaoArquivo = this.fileRead.getConfiguracao();
            //inicialização do objeto impressoes
            this.impressoes = this.fileRead.getImpressoes();
            // seto os dados para o formulario de cadastro
            this.txtCaminhoRelatorios.setText(this.impressoes.getCaminhoAbsoluto().replace("/", "\\"));
            this.txtServidor.setText(this.configuracaoArquivo.getServer());
            this.txtBancoDados.setText(this.configuracaoArquivo.getDataBase());
            this.txtUser.setText(this.configuracaoArquivo.getUser());
            this.txtSenha.setText(this.configuracaoArquivo.getPassword());
            // itero toda a lista de itens que contém as portas disponiveis  
            for (int x = 0; x < this.selectPort.getItemCount(); x++) {
                // verifico se a posicao iterada for igual ao atributo porta tcp do objeto configuracao
                if (this.selectPort.getSelectedItem().equals(this.configuracaoArquivo.getPortaTcp())) {
                    // seto para o combobox a posicao selecionada
                    this.selectPort.setSelectedIndex(x);
                }
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaConfiguracao.class, "public TelaConfiguracao()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        }
    }

    /**
     * metodo responsavel por tornar editavel ou nao os botoes da barra de
     * cadastro
     *
     * @param editavel recebe um dado booleano informando se o mesmo está ou não
     * editavel
     */
    private void botoesEditaveis(boolean editavel) {
        try {
            this.btnSalvar.setEnabled(editavel);
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaConfiguracao.class, "private void botoesEditaveis()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        }
    }

    /**
     * metodo responsavle por tornar editavel os campos do formulario de
     * cadastro de configuração
     *
     * @param habilitar recebe como parametro um dado booleano informando se o
     * mesmo está ou não editavel
     */
    private void habilitarCampos(boolean habilitar) {
        try {
            if (habilitar) {
                this.txtBancoDados.setEnabled(habilitar);
                this.txtCaminhoRelatorios.setEnabled(habilitar);
                this.txtSenha.setEnabled(habilitar);
                this.txtServidor.setEnabled(habilitar);
                this.txtUser.setEnabled(habilitar);
                this.selectBancoDados.setEnabled(habilitar);
                this.selectPort.setEnabled(habilitar);
                this.btnCaminhoRelatorio.setEnabled(habilitar);
                this.txtServidor.requestFocus();
            } else {
                this.txtBancoDados.setEnabled(habilitar);
                this.txtCaminhoRelatorios.setEnabled(habilitar);
                this.txtSenha.setEnabled(habilitar);
                this.txtServidor.setEnabled(habilitar);
                this.txtUser.setEnabled(habilitar);
                this.selectBancoDados.setEnabled(habilitar);
                this.btnCaminhoRelatorio.setEnabled(habilitar);
                this.selectPort.setEnabled(habilitar);
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaConfiguracao.class, "private void habilitarCampos()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        }
    }

    /**
     * metodo responsavel por validar se todos os campos do formulario estõ
     * preenchidos
     *
     * @return retorna um dado booleano , informando se todos os campos esão
     * preenchidos ou nao
     */
    private boolean validarSalvar() {
        try {
            // Se o conteudo do Campo de Texto for Vazio
            if (this.txtServidor.getText().isEmpty()) {
                // Exibe a mensagem solicitando ao usuario para informar o nome ou ip do servidor
                JOptionPane.showMessageDialog(this, "O ip/nome do servidor devem ser informados!");
                // Repasso o foco(cursor do mouse) para o campo de texto 
                this.txtServidor.requestFocus();
                return false;
            } else {
                // seto para o objeto configuracao o conteudo do campo servidor
                this.configuracaoArquivo.setServer(this.txtServidor.getText());
            }

            // se o item selecionado for diferente do que indice zero onde está a descrição para o Mysql 
            if (this.selectBancoDados.getSelectedIndex() != 0) {
                // exibe a mensagem de notificação para o usuario 
                int resposta = JOptionPane.showConfirmDialog(this, "Sistema gerenciador de banco de dados\n"
                        + "não está homologado.Certifique-se\n"
                        + "Deseja Continuar?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.NO_OPTION) {
                    // seleciono o indice zero que é o indice que contém a descricao do SGDB MySQL 
                    this.selectBancoDados.setSelectedIndex(0);
                    // repasso o foco para o combobox
                    this.selectBancoDados.requestFocus();
                    return true;
                }
            } else {
                // seto para o atributo dialect do objeto configuracao o objeto selecionado   
                this.configuracaoArquivo.setDialect(this.selectBancoDados.getSelectedItem().toString());
            }

            // se o campo banco de dados for vazio
            if (this.txtBancoDados.getText().isEmpty()) {
                // exibo a mensagem para o usuário
                JOptionPane.showMessageDialog(this, "O nome do banco de dados deve ser informado!");
                // retomo o foco da janela para o campo de texto
                this.txtBancoDados.requestFocus();
                return false;
            } else {
                // seto o conteudo(string) do campo para o objeto configuracao
                configuracaoArquivo.setDataBase(this.txtBancoDados.getText());
            }

            // se o indice selecionado for diferente do indice zero, ou seja da porta padrão do MySQL 
            if (this.selectPort.getSelectedIndex() != 0) {
                // exibe a mensagem de notificação para o usuario 
                int resposta = JOptionPane.showConfirmDialog(this, " A porta TCP selecionada não se trata\n"
                        + " da porta padão para o sistema gerenciador\n"
                        + " de banco de dados selecionado.Certifique-se\n"
                        + " que ele realmente está instalado na porta apontada.\n"
                        + " deseja continuar ?", "Confirmação", JOptionPane.YES_NO_OPTION);
                // se a resposta do usuario for sim
                if (resposta == JOptionPane.YES_OPTION) {
                    // seto para o atibuto porta tcp  do objeto configuracao o item selecionado
                    this.configuracaoArquivo.setPortaTcp(Integer.parseInt(this.selectPort.getSelectedItem().toString()));
                    //rescreve o arquivo de configuracao
                    return true;
                } else {
                    // repasso o foco para o combobox
                    this.selectPort.requestFocus();
                }
            } else {
                // seto para o atibuto porta tcp  do objeto configuracao o item selecionado
                configuracaoArquivo.setPortaTcp(Integer.parseInt(this.selectPort.getSelectedItem().toString()));
            }

            // se o campo usuario for vazio    
            if (this.txtUser.getText().isEmpty()) {
                // exibo a mensagem de notificação para o usuario
                JOptionPane.showMessageDialog(this, " O usuário deve ser informado!");
                // Repasso o foco(cursor do mouse) para o campo de texto 
                this.txtUser.requestFocus();
                return false;
            } else {
                // seto para o atributo usuario do objeto configuracao  o conteudo do campo de texto user 
                this.configuracaoArquivo.setUser(this.txtUser.getText());
            }

            // se o campo senha for vazio
            if (this.txtSenha.getText().isEmpty()) {
                // exibo a mensagem para o usuario informando que o campo senha esta vazio e se mesmo assim ele deseja proseguir
                int resposta = JOptionPane.showConfirmDialog(this, "A senha não foi informada,\n"
                        + "deseja continuar mesmo assim?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_NO_OPTION) {
                    // se a responsta do usuario for sim, entao sete para o atributo senha o texto contido no campo txtsenha
                    this.configuracaoArquivo.setPassword(this.txtSenha.getText());
                    this.fileRead.setImpressoes(impressoes);
                    //rescrevo o arquivo
                    this.fileRead.setConfiguracao(configuracaoArquivo);
                    return false;
                } else {
                    // senão repasse o foco para o campo de texto
                    this.txtSenha.requestFocus();
                }
            } else {
                // seto para o atributo  senha do objeto configuracao o texto contidos no campo txtsenha  
                this.configuracaoArquivo.setPassword(this.txtSenha.getText());
            }

            if (!txtCaminhoRelatorios.getText().isEmpty()) {
                impressoes.setCaminhoAbsoluto(txtCaminhoRelatorios.getText().replace("\\", "/"));
                return true;
            } else {
                JOptionPane.showMessageDialog(this, "Informe o caminho dos relatórios!");
                return false;
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaConfiguracao.class, "private boolean validarSalvar()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelFundoConfiguracao = new javax.swing.JPanel();
        ToolBarSuperior = new javax.swing.JToolBar();
        btnAltera = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        painelConexao = new javax.swing.JPanel();
        lblServidor = new javax.swing.JLabel();
        txtServidor = new javax.swing.JTextField();
        lblSGDB = new javax.swing.JLabel();
        selectBancoDados = new javax.swing.JComboBox();
        txtBancoDados = new javax.swing.JTextField();
        lblbancoDados = new javax.swing.JLabel();
        lblPortaTCp = new javax.swing.JLabel();
        selectPort = new javax.swing.JComboBox();
        painelSecurity = new javax.swing.JPanel();
        lblUser = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();
        lblSenha = new javax.swing.JLabel();
        painelCaminhoRelatorio = new javax.swing.JPanel();
        txtCaminhoRelatorios = new javax.swing.JTextField();
        btnCaminhoRelatorio = new javax.swing.JButton();
        lblBancoDados = new javax.swing.JLabel();
        lblRelatorios = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PARKING MANAGEMENT SYSTEM - Configurações");
        setPreferredSize(new java.awt.Dimension(550, 420));
        setResizable(false);

        painelFundoConfiguracao.setBackground(new java.awt.Color(255, 255, 255));

        ToolBarSuperior.setBackground(new java.awt.Color(255, 255, 0));
        ToolBarSuperior.setRollover(true);

        btnAltera.setBackground(new java.awt.Color(255, 255, 255));
        btnAltera.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAltera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/parking/imagens/ALTERAR_INTERNALFRAME.png"))); // NOI18N
        btnAltera.setText("ALTERAR");
        btnAltera.setFocusable(false);
        btnAltera.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAltera.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAltera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlteraActionPerformed(evt);
            }
        });
        ToolBarSuperior.add(btnAltera);

        btnSalvar.setBackground(new java.awt.Color(255, 255, 255));
        btnSalvar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/parking/imagens/SALVAR_INTERNALFRAME.png"))); // NOI18N
        btnSalvar.setText("SALVAR");
        btnSalvar.setFocusable(false);
        btnSalvar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalvar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        ToolBarSuperior.add(btnSalvar);

        btnSair.setBackground(new java.awt.Color(255, 255, 255));
        btnSair.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/parking/imagens/SAIR_INTERNAL FRAME.png"))); // NOI18N
        btnSair.setText("FECHAR");
        btnSair.setFocusable(false);
        btnSair.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSair.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        ToolBarSuperior.add(btnSair);

        painelConexao.setBackground(new java.awt.Color(255, 255, 255));
        painelConexao.setBorder(javax.swing.BorderFactory.createTitledBorder("Conexão"));

        lblServidor.setBackground(new java.awt.Color(255, 255, 255));
        lblServidor.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblServidor.setText("Servidor");
        lblServidor.setToolTipText("");

        txtServidor.setToolTipText("Configure o Endereço IP ou Nome do Seu Servidor de Banco de Dados");

        lblSGDB.setBackground(new java.awt.Color(255, 255, 255));
        lblSGDB.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblSGDB.setText("SGDB");

        selectBancoDados.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MySQL" }));

        txtBancoDados.setToolTipText("Informe o nome do Banco de Dados");

        lblbancoDados.setBackground(new java.awt.Color(255, 255, 255));
        lblbancoDados.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblbancoDados.setText("Banco De Dados");
        lblbancoDados.setToolTipText("");

        lblPortaTCp.setBackground(new java.awt.Color(255, 255, 255));
        lblPortaTCp.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblPortaTCp.setText("Porta TCP");

        selectPort.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "3306", "3307", "3308", "3309", "3310", "3311", "3312" }));

        javax.swing.GroupLayout painelConexaoLayout = new javax.swing.GroupLayout(painelConexao);
        painelConexao.setLayout(painelConexaoLayout);
        painelConexaoLayout.setHorizontalGroup(
            painelConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelConexaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblServidor)
                    .addComponent(lblbancoDados)
                    .addComponent(txtServidor, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                    .addComponent(txtBancoDados))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(painelConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(selectPort, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(selectBancoDados, 0, 87, Short.MAX_VALUE)
                        .addComponent(lblSGDB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblPortaTCp))
                .addContainerGap())
        );
        painelConexaoLayout.setVerticalGroup(
            painelConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelConexaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblServidor)
                    .addComponent(lblSGDB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtServidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectBancoDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(painelConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPortaTCp)
                    .addComponent(lblbancoDados))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBancoDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        painelSecurity.setBackground(new java.awt.Color(255, 255, 255));
        painelSecurity.setBorder(javax.swing.BorderFactory.createTitledBorder("Segurança"));

        lblUser.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblUser.setText("Usuário");

        txtUser.setToolTipText("Insira o Usuário utilizado para Conexão Com o Banco de Dados");

        txtSenha.setToolTipText("Informe a Senha do Usuario Registrado");

        lblSenha.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblSenha.setText("Senha");

        javax.swing.GroupLayout painelSecurityLayout = new javax.swing.GroupLayout(painelSecurity);
        painelSecurity.setLayout(painelSecurityLayout);
        painelSecurityLayout.setHorizontalGroup(
            painelSecurityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelSecurityLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelSecurityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUser)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(painelSecurityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSenha))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        painelSecurityLayout.setVerticalGroup(
            painelSecurityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelSecurityLayout.createSequentialGroup()
                .addGroup(painelSecurityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUser)
                    .addComponent(lblSenha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelSecurityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        painelCaminhoRelatorio.setBackground(new java.awt.Color(255, 255, 255));
        painelCaminhoRelatorio.setBorder(javax.swing.BorderFactory.createTitledBorder("Relatórios"));

        txtCaminhoRelatorios.setEditable(false);
        txtCaminhoRelatorios.setBackground(new java.awt.Color(255, 255, 255));

        btnCaminhoRelatorio.setBackground(new java.awt.Color(255, 255, 255));
        btnCaminhoRelatorio.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCaminhoRelatorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/parking/imagens/Pesquisar_TelaConsulta.png"))); // NOI18N
        btnCaminhoRelatorio.setText("Relatórios");
        btnCaminhoRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCaminhoRelatorioActionPerformed(evt);
            }
        });
        btnCaminhoRelatorio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCaminhoRelatorioKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout painelCaminhoRelatorioLayout = new javax.swing.GroupLayout(painelCaminhoRelatorio);
        painelCaminhoRelatorio.setLayout(painelCaminhoRelatorioLayout);
        painelCaminhoRelatorioLayout.setHorizontalGroup(
            painelCaminhoRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelCaminhoRelatorioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCaminhoRelatorios, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCaminhoRelatorio)
                .addGap(76, 76, 76))
        );
        painelCaminhoRelatorioLayout.setVerticalGroup(
            painelCaminhoRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelCaminhoRelatorioLayout.createSequentialGroup()
                .addGroup(painelCaminhoRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCaminhoRelatorios, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCaminhoRelatorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        lblBancoDados.setBackground(new java.awt.Color(255, 255, 255));
        lblBancoDados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/parking/imagens/TELA_BANCO_DADOS_CONFIGURACAO.png"))); // NOI18N

        lblRelatorios.setBackground(new java.awt.Color(255, 255, 255));
        lblRelatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/parking/imagens/TELA_IMPRESSOES_CONFIGURACAO.png"))); // NOI18N

        javax.swing.GroupLayout painelFundoConfiguracaoLayout = new javax.swing.GroupLayout(painelFundoConfiguracao);
        painelFundoConfiguracao.setLayout(painelFundoConfiguracaoLayout);
        painelFundoConfiguracaoLayout.setHorizontalGroup(
            painelFundoConfiguracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ToolBarSuperior, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(painelFundoConfiguracaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelFundoConfiguracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelFundoConfiguracaoLayout.createSequentialGroup()
                        .addComponent(painelConexao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblBancoDados))
                    .addGroup(painelFundoConfiguracaoLayout.createSequentialGroup()
                        .addGroup(painelFundoConfiguracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(painelCaminhoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(painelSecurity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblRelatorios)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        painelFundoConfiguracaoLayout.setVerticalGroup(
            painelFundoConfiguracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFundoConfiguracaoLayout.createSequentialGroup()
                .addGroup(painelFundoConfiguracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelFundoConfiguracaoLayout.createSequentialGroup()
                        .addComponent(ToolBarSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(painelConexao, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelFundoConfiguracaoLayout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(lblBancoDados)))
                .addGroup(painelFundoConfiguracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelFundoConfiguracaoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(painelSecurity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(painelCaminhoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelFundoConfiguracaoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(lblRelatorios)
                        .addGap(51, 51, 51))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelFundoConfiguracao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(painelFundoConfiguracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAlteraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlteraActionPerformed
        try {
            this.botoesEditaveis(true);
            this.btnAltera.setEnabled(false);
            this.habilitarCampos(true);
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "private void btnAlteraActionPerformed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }//GEN-LAST:event_btnAlteraActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            if (validarSalvar()) {
                this.fileRead.rescreverArquivo();
                JOptionPane.showMessageDialog(this, "O sistema será finalizado.");
                System.exit(0);
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaVaga.class, "");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        try {
            int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente sair ?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                this.dispose();
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaVaga.class, "private void btnSairActionPerformed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnCaminhoRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaminhoRelatorioActionPerformed
        try {
            //inicializo o objeto do tipo JFileChooser
            this.explorer = new JFileChooser();
            //perimito apenas a seleção de arquivos
            this.explorer.setFileSelectionMode(JFileChooser.FILES_ONLY);
            // defino os tipos de arquivos que podem ser selecionados pelo usuario
            explorer.setFileFilter(new FileNameExtensionFilter("Arquivo Jasper", "jasper"));
            // defino os tipos de arquivos que podem ser selecionados pelo usuario
            explorer.addChoosableFileFilter(new FileNameExtensionFilter("Arquivo Jasper", "jasper", "jasper"));
            // exibo a tela de busca pelos arquivos
            this.explorer.showOpenDialog(this);
            //inicializo um objeto do tipo Arquivo capturando o arquivo selecionado pelo usuario
            File arquivoSelecionado = this.explorer.getSelectedFile();
            //repasso o arquivo selecionado para o banco de dados
            this.txtCaminhoRelatorios.setText(arquivoSelecionado.getAbsolutePath().replace("/", "\\"));
            //finalizo a execução da tela de busca de arquivo
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaConfiguracao.class, "private void btnCaminhoRelatorioActionPerformed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        }
    }//GEN-LAST:event_btnCaminhoRelatorioActionPerformed

    private void btnCaminhoRelatorioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCaminhoRelatorioKeyPressed
        try {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                //inicializo o objeto do tipo JFileChooser
                this.explorer = new JFileChooser();
                //perimito apenas a seleção de arquivos
                this.explorer.setFileSelectionMode(JFileChooser.FILES_ONLY);
                // defino os tipos de arquivos que podem ser selecionados pelo usuario
                explorer.setFileFilter(new FileNameExtensionFilter("Arquivo Jasper", "jasper"));
                // defino os tipos de arquivos que podem ser selecionados pelo usuario
                explorer.addChoosableFileFilter(new FileNameExtensionFilter("Arquivo Jasper", "jasper", "jasper"));
                // exibo a tela de busca pelos arquivos
                this.explorer.showOpenDialog(this);
                //inicializo um objeto do tipo Arquivo capturando o arquivo selecionado pelo usuario
                File arquivoSelecionado = this.explorer.getSelectedFile();
                //repasso o arquivo selecionado para o banco de dados
                this.txtCaminhoRelatorios.setText(arquivoSelecionado.getAbsolutePath().replace("/", "\\"));
                //finalizo a execução da tela de busca de arquivo
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaConfiguracao.class, "private void btnCaminhoRelatorioActionPerformed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        }
    }//GEN-LAST:event_btnCaminhoRelatorioKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar ToolBarSuperior;
    private javax.swing.JButton btnAltera;
    private javax.swing.JButton btnCaminhoRelatorio;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel lblBancoDados;
    private javax.swing.JLabel lblPortaTCp;
    private javax.swing.JLabel lblRelatorios;
    private javax.swing.JLabel lblSGDB;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblServidor;
    private javax.swing.JLabel lblUser;
    private javax.swing.JLabel lblbancoDados;
    private javax.swing.JPanel painelCaminhoRelatorio;
    private javax.swing.JPanel painelConexao;
    private javax.swing.JPanel painelFundoConfiguracao;
    private javax.swing.JPanel painelSecurity;
    private javax.swing.JComboBox selectBancoDados;
    private javax.swing.JComboBox selectPort;
    private javax.swing.JTextField txtBancoDados;
    private javax.swing.JTextField txtCaminhoRelatorios;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtServidor;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
