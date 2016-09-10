package com.parking.telas;

import com.parking.entity.Vaga;
import com.parking.files.PrintLogger;
import com.parking.frame.TelaPrincipal;
import com.parking.regrasnegocios.Persistencia;
import com.parking.telasConsulta.TelaConsultaVaga;
import java.awt.Color;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.Collections;
import javax.swing.JOptionPane;

/**
 *
 * @author Diovani Bernardi
 */
public class TelaVaga extends javax.swing.JDialog {

    //Objeto responsavel por imprimir em arquivo as eventuais excessões geradas pela aplicação
    private PrintLogger logger = new PrintLogger();
    //Objeto responsavel por armazenar um objeto do tipo tela de consulta de informações
    private TelaConsultaVaga telaConsultaVaga;
    //Objeto responsavel por armazenar uma instancia da tela principal do software
    private TelaPrincipal telaPrincipal;
    //Objeto responsavel por armazenar uma instancia de um objeto que realizara a iteracao com o banco de dados
    private Persistencia persistencia = new Persistencia();
    //Objeto responsavel por armazenar as informações de um objeto do tipo vaga
    private Vaga vaga = new Vaga();
    //Objeto responsavel por armazenar uma instancia da tela de cadastro de reserva
    private TelaReserva telaReserva;

    /**
     *
     * @param parent
     * @param modal
     * @param persistencia
     * @param telaPrincipal
     */
    public TelaVaga(java.awt.Frame parent, boolean modal, Persistencia persistencia, TelaPrincipal telaPrincipal) {
        super(parent, modal);
        try {
            initComponents();
            //inicializacao dos objetos recebidos por parametro
            this.persistencia = persistencia;
            this.telaPrincipal = telaPrincipal;
            //desabilito os botoes do formulario
            this.botoesEditaveis(false);
            //desabilito a edicao do formulario
            this.habilitarCampos(false);
            //metodo utilizado para tratar os eventos de tab no campo de texto
            this.txtNumeroVaga.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, " public TelaVaga(");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    /**
     *
     * @param parent
     * @param modal
     * @param persistencia
     * @param telaReserva
     */
    public TelaVaga(java.awt.Frame parent, boolean modal, Persistencia persistencia, TelaReserva telaReserva) {
        super(parent, modal);
        try {
            initComponents();
            //inicializacao dos objetos recebidos por parametro
            this.persistencia = persistencia;
            this.telaReserva = telaReserva;
            //desabilito os botoes do formulario
            this.botoesEditaveis(false);
            //desabilito a edicao do formulario
            this.habilitarCampos(false);
            //metodo utilizado para tratar os eventos de tab no campo de texto
            this.txtNumeroVaga.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, " public TelaVaga(");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    /**
     * metodo que tem a responsabilidade de verificar se todas as informações de
     * um formulario estao preenchidas
     *
     * @return um dado booleano, sendo true se todos os campos estao preechidos
     * e false se houver algum
     */
    private boolean validarInformacoes() {
        try {
            if (txtReferencia.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "INFORME A REFERÊNCIA DA VAGA !");
                this.txtReferencia.requestFocus();
                return false;
            } else if (txtNumeroVaga.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "INFORME O NÚMERO DA VAGA !");
                this.txtNumeroVaga.requestFocus();
                return false;
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaVaga.class, "private boolean validarInformacoes()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
        return true;
    }

    /**
     * Metodo responsavel por tornar editavel os botoes da paleta de cadastro
     *
     * @param visivel recebe um dado booleano, sendo verdadeiro se for editavel
     * e falso se nao for
     */
    private void botoesEditaveis(boolean visivel) {
        try {
            this.btnSalvar.setEnabled(visivel);
            this.btnAltera.setEnabled(visivel);
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaVaga.class, " private void botoesEditaveis()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    /**
     * Metodo responsavel por tornar editavel o formulario de cadastro
     *
     * @param habilitar recebe um dado booleano, sendo verdadeiro se for
     * editavel e falso se nao for
     */
    private void habilitarCampos(boolean habilitar) {
        try {
            if (habilitar) {
                this.txtNumeroVaga.setEnabled(habilitar);
                this.txtReferencia.setEnabled(habilitar);
                this.txtReferencia.requestFocus();
            } else {
                this.txtNumeroVaga.setEnabled(habilitar);
                this.txtReferencia.setEnabled(habilitar);
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaVaga.class, "private void habilitarCampos()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    /**
     * metodo que tem a responsabilidade de limpar todos os dados contidos em um
     * formulario
     */
    private void limparCampos() {
        try {
            this.txtCodigoVaga.setText("");
            this.txtNumeroVaga.setText("");
            this.txtReferencia.setText("");
            this.txtStatus.setText("");
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "private void limparCampos()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    /**
     * metodo responsavel por receber um objeto vaga selecionado na tela de
     * consulta e informar o objeto no formulario
     *
     * @param vaga recebe como parametro um objeto do tipo
     */
    public void indicarVaga(Vaga vaga) {
        try {
            //inicializo o objeto do tipo vaga
            this.vaga = vaga;
            this.txtCodigoVaga.setText(String.valueOf(this.vaga.getCodigo()));
            this.txtReferencia.setText(this.vaga.getReferencia());
            this.txtNumeroVaga.setText(String.valueOf(this.vaga.getNumeroVaga()));
            if (this.vaga.isStatus()) {
                this.txtStatus.setText("LIVRE");
                this.txtStatus.setForeground(Color.GREEN);
            } else {
                this.txtStatus.setText("OCUPADO");
                this.txtStatus.setForeground(Color.RED);
            }
            //habilito o botao alterar
            this.btnAltera.setEnabled(true);
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "public void indicarVaga()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    /**
     * metodo responsavel por configurar os dados do formulario para o objeto
     *
     * @param codigo
     * @param referencia
     * @param numero
     * @param situacao
     */
    public void inserirVaga(String codigo, String referencia, String numero, String situacao) {
        try {
            this.vaga.setNumeroVaga(Integer.parseInt(numero));
            this.vaga.setReferencia(referencia);
            if (!codigo.isEmpty()) {
                this.vaga.setCodigo(Integer.parseInt(codigo));
            }
            this.vaga.setStatus(situacao.equals("LIVRE") ? true : false);
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaVaga.class, "public void inserirVaga()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelFundoVaga = new javax.swing.JPanel();
        ToolBarSuperior = new javax.swing.JToolBar();
        btnAltera = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnPesquisar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        txtCodigoVaga = new javax.swing.JFormattedTextField();
        txtNumeroVaga = new javax.swing.JFormattedTextField();
        txtReferencia = new javax.swing.JFormattedTextField();
        txtStatus = new javax.swing.JFormattedTextField();
        lblCodigo = new javax.swing.JLabel();
        lblReferencia = new javax.swing.JLabel();
        lblNumeroVaga = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        lblImagem = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PARKING MANAGEMENT SYSTEM - Cadastro de Vaga");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                telavagaWindowsClosing(evt);
            }
        });

        painelFundoVaga.setBackground(new java.awt.Color(255, 255, 255));

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

        btnNovo.setBackground(new java.awt.Color(255, 255, 255));
        btnNovo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/parking/imagens/NOVO_INTERNALFRAME.png"))); // NOI18N
        btnNovo.setText("NOVO");
        btnNovo.setFocusable(false);
        btnNovo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNovo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        ToolBarSuperior.add(btnNovo);

        btnPesquisar.setBackground(new java.awt.Color(255, 255, 255));
        btnPesquisar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/parking/imagens/PESQUISAR_INTERNALFRAME.png"))); // NOI18N
        btnPesquisar.setText("PESQUISAR");
        btnPesquisar.setFocusable(false);
        btnPesquisar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPesquisar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });
        ToolBarSuperior.add(btnPesquisar);

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

        txtCodigoVaga.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigoVaga.setEnabled(false);

        txtNumeroVaga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNumeroVagaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroVagaKeyTyped(evt);
            }
        });

        txtStatus.setEditable(false);
        txtStatus.setBackground(new java.awt.Color(255, 255, 255));
        txtStatus.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtStatus.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lblCodigo.setBackground(new java.awt.Color(255, 255, 255));
        lblCodigo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCodigo.setText("Código");

        lblReferencia.setBackground(new java.awt.Color(255, 255, 255));
        lblReferencia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblReferencia.setText("Referência");

        lblNumeroVaga.setBackground(new java.awt.Color(255, 255, 255));
        lblNumeroVaga.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblNumeroVaga.setText("Número Vaga");
        lblNumeroVaga.setToolTipText("");

        lblStatus.setBackground(new java.awt.Color(255, 255, 255));
        lblStatus.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblStatus.setText("Situação");

        lblImagem.setBackground(new java.awt.Color(255, 255, 255));
        lblImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/parking/imagens/vaga.png"))); // NOI18N

        javax.swing.GroupLayout painelFundoVagaLayout = new javax.swing.GroupLayout(painelFundoVaga);
        painelFundoVaga.setLayout(painelFundoVagaLayout);
        painelFundoVagaLayout.setHorizontalGroup(
            painelFundoVagaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelFundoVagaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(ToolBarSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(painelFundoVagaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelFundoVagaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelFundoVagaLayout.createSequentialGroup()
                        .addGroup(painelFundoVagaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigoVaga, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCodigo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(painelFundoVagaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblReferencia)
                            .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(painelFundoVagaLayout.createSequentialGroup()
                        .addGroup(painelFundoVagaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumeroVaga, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNumeroVaga))
                        .addGap(18, 18, 18)
                        .addGroup(painelFundoVagaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblStatus)
                            .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelFundoVagaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        painelFundoVagaLayout.setVerticalGroup(
            painelFundoVagaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFundoVagaLayout.createSequentialGroup()
                .addComponent(ToolBarSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelFundoVagaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigo)
                    .addComponent(lblReferencia))
                .addGap(4, 4, 4)
                .addGroup(painelFundoVagaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigoVaga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(painelFundoVagaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumeroVaga)
                    .addComponent(lblStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelFundoVagaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumeroVaga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblImagem)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelFundoVaga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelFundoVaga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAlteraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlteraActionPerformed
        try {
            // se o campo de texto for vazio
            if (this.txtCodigoVaga.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Não é permitido alterar um registro não salvo!");
                this.habilitarCampos(false);
                this.botoesEditaveis(false);
            } else {
                this.habilitarCampos(true);
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "private void btnAlteraActionPerformed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }//GEN-LAST:event_btnAlteraActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        try {
            if (!txtCodigoVaga.getText().isEmpty()) {
                int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente iniciar um novo cadastro ?", "CONFIRMAÇÃO", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    //habilito os campos do formulario
                    this.habilitarCampos(true);
                    //habilito os botoes da paleta de cadastro
                    this.botoesEditaveis(true);
                    //limpo os campso
                    this.limparCampos();
                    this.txtStatus.setText("LIVRE");
                    this.txtStatus.setForeground(Color.GREEN);
                }
            } else {
                //habilito os campos do formulario
                this.habilitarCampos(true);
                //habilito os botoes da paleta de cadastro
                this.botoesEditaveis(true);
                //limpo os campso
                this.limparCampos();
                this.txtStatus.setText("LIVRE");
                this.txtStatus.setForeground(Color.GREEN);
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaVaga.class, "private void btnNovoActionPerformed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        try {
            //instancio a tela de cadastro de clientes
            this.telaConsultaVaga = new TelaConsultaVaga(telaPrincipal, true, this, persistencia);
            //centralizo a exibicao do componente no JFrame
            this.telaConsultaVaga.setLocationRelativeTo(null);
            //torno visivel a tela de cadastro de clientes
            this.telaConsultaVaga.setVisible(true);
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaVaga.class, "private void btnPesquisarActionPerformed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            // se todas as informações estiverem preenchidas
            if (validarInformacoes()) {
                //chamada ao metodo responsavel por setar os atributos para o objeto vaga
                this.inserirVaga(txtCodigoVaga.getText(), txtReferencia.getText(), txtNumeroVaga.getText(), txtStatus.getText());
                if (txtCodigoVaga.getText().isEmpty()) {
                    // executo uma consulta no banco de dados para verificar se exite um vaga cadastrado com o numero da vaga informado 
                    Vaga v = this.persistencia.retornarVagaCadastrada(txtNumeroVaga.getText());
                    // se o vaga retornado do banco de dados nao for nulo
                    if (v != null) {
                        int resposta = JOptionPane.showConfirmDialog(this, "Vaga já cadastrada. Deseja visualizá-lo ?", "Confirmação", JOptionPane.YES_NO_OPTION);
                        // se a resposta do usuario for sim
                        if (resposta == JOptionPane.YES_OPTION) {
                            //inicializo o objeto vaga
                            this.vaga = v;
                            //seto o objeto selecionado para o formulario
                            this.indicarVaga(vaga);
                        }
                    } else {
                        persistencia.inserirObjeto(vaga);
                        JOptionPane.showMessageDialog(this, "Registro inserido com sucesso!");
                        this.botoesEditaveis(false);
                        this.limparCampos();
                        this.habilitarCampos(false);
                    }
                } else {
                    persistencia.atualizarObjeto(vaga);
                    JOptionPane.showMessageDialog(this, "Registro atualizado com sucesso!");
                    this.botoesEditaveis(false);
                    this.limparCampos();
                    this.habilitarCampos(false);
                }
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
                // se a tela de reserva estiver sido instanciada   
                if (telaReserva != null) {
                    //chamada ao metodo responsavel por remover todas as vagas do painel
                    this.telaReserva.removerVagas();
                    //chamada ao metodo responsavel por adicionar todas as vagas cadastradas ao painel
                    this.telaReserva.carregarQuantidadeVagasCadastradas();
                    //repinto a tela atual
                    this.repaint();
                }
                this.dispose();
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaVaga.class, "private void btnSairActionPerformed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }//GEN-LAST:event_btnSairActionPerformed

    private void txtNumeroVagaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroVagaKeyTyped
        try {
            String numeros = "0123456789";
            // se a tecla pressionada nao for um numero
            if (!numeros.contains(String.valueOf(evt.getKeyChar()))) {
                //elimino o evento gerado
                evt.consume();
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaVaga.class, " private void txtNumeroVagaKeyTyped()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }//GEN-LAST:event_txtNumeroVagaKeyTyped

    private void telavagaWindowsClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_telavagaWindowsClosing
        try {
            // se a tela de reserva estiver sido instanciada   
            if (telaReserva != null) {
                //chamada ao metodo responsavel por remover todas as vagas do painel
                this.telaReserva.removerVagas();
                //chamada ao metodo responsavel por adicionar todas as vagas cadastradas ao painel
                this.telaReserva.carregarQuantidadeVagasCadastradas();
                //repinto a tela atual
                this.repaint();
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaVaga.class, " private void telavagaWindowsClosing()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }//GEN-LAST:event_telavagaWindowsClosing

    private void txtNumeroVagaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroVagaKeyPressed
        try {
            // se a tecla pressionada for o enter
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                // executo uma consulta no banco de dados para verificar se exite uma vaga cadastrada com o numero de vaga informado 
                Vaga v = this.persistencia.retornarVagaCadastrada(txtNumeroVaga.getText());
                // se a vaga retornado do banco de dados nao for nulo
                if (v != null) {
                    int resposta = JOptionPane.showConfirmDialog(this, "Vaga já cadastrada. Deseja visualizá-lo ?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    // se a resposta do usuario for sim
                    if (resposta == JOptionPane.YES_OPTION) {
                        //inicializo o objeto vaga
                        this.vaga = v;
                        //seto o objeto selecionado para o formulario
                        this.indicarVaga(vaga);
                    }
                } else {
                    // seto o foco para o botao salvar 
                    this.btnSalvar.requestFocus();
                }
                // se a tecla pressionada for o tab    
            } else if (evt.getKeyCode() == KeyEvent.VK_TAB) {
                // executo uma consulta no banco de dados para verificar se exite um vaga cadastrado com o numero da vaga informado 
                Vaga v = this.persistencia.retornarVagaCadastrada(txtNumeroVaga.getText());
                // se o vaga retornado do banco de dados nao for nulo
                if (v != null) {
                    int resposta = JOptionPane.showConfirmDialog(this, "Vaga já cadastrada. Deseja visualizá-lo ?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    // se a resposta do usuario for sim
                    if (resposta == JOptionPane.YES_OPTION) {
                        //inicializo o objeto vaga
                        this.vaga = v;
                        //seto o objeto selecionado para o formulario
                        this.indicarVaga(vaga);
                    }
                } else {
                    // seto o foco para o botao salvar
                    this.btnSalvar.requestFocus();
                }
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaVaga.class, "private void txtNumeroVagaKeyPressed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }//GEN-LAST:event_txtNumeroVagaKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar ToolBarSuperior;
    private javax.swing.JButton btnAltera;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblImagem;
    private javax.swing.JLabel lblNumeroVaga;
    private javax.swing.JLabel lblReferencia;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JPanel painelFundoVaga;
    private javax.swing.JFormattedTextField txtCodigoVaga;
    private javax.swing.JFormattedTextField txtNumeroVaga;
    private javax.swing.JFormattedTextField txtReferencia;
    private javax.swing.JFormattedTextField txtStatus;
    // End of variables declaration//GEN-END:variables
}
