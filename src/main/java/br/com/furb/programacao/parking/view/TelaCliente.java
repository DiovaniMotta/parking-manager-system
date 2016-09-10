package br.com.furb.programacao.parking.view;

import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.Collections;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Diovani Bernardi
 */
public class TelaCliente extends javax.swing.JDialog {

    //Objeto responsavel por realizar a interação com o banco de dados
    private Persistencia persistencia;
    //Objeto responsavel por exibir os resultados de uma consulta no banco de dados
    private TelaConsultaCliente telaConsultaCliente;
    //Objeto responsavel por armazenar uma instancia da tela principal do programa
    private TelaPrincipal telaPrincipal;
    //Objeto responsavel por armazenar as caracteristicas de um objeto do tipo cliente
    private Cliente cliente = new Cliente();
    //Objeto responsavel por armazenar uma instancia da tela de cadastro de reserva;
    private TelaReserva telaReserva;
    //Objeto responsavel por realizar a validação dos campos do formulario
    private MaskFormatter formatter;
    //Objeto responsavel por imprimir em arquivo as eventuais excessões geradas pela aplicação
    private PrintLogger logger = new PrintLogger();

    /**
     *
     * @param parent
     * @param modal
     * @param persistencia
     * @param principal
     */
    public TelaCliente(java.awt.Frame parent, boolean modal, Persistencia persistencia, TelaPrincipal principal) {
        super(parent, modal);
        try {
            //centralizo o componente
            super.setLocationRelativeTo(null);
            //inicializacao do objeto persistencia
            this.persistencia = persistencia;
            //inicializacao do objeto persistencia
            this.telaPrincipal = principal;
            //metodo responsavel por inicializar os componentes  graficos da tela
            initComponents();
            //desabilito os botoes do formulario
            this.botoesEditaveis(false);
            //desabilito a edicao do formulario
            this.habilitarCampos(false);
            //metodo utilizado para tratar os eventos de tab no campo de texto
            this.txtCNPJ.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
            this.txtCNPJ.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("###.###.###-##")));
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaCliente.class, "public TelaCliente()");
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
    public TelaCliente(java.awt.Frame parent, boolean modal, Persistencia persistencia, TelaReserva telaReserva) {
        super(parent, modal);
        try {
            //centralizo o componente
            super.setLocationRelativeTo(null);
            //inicializacao dos objeto recebidos por parametro
            this.persistencia = persistencia;
            this.telaReserva = telaReserva;
            //metodo responsavel por inicializar os componentes  graficos da tela
            initComponents();
            //desabilito os botoes do formulario
            this.botoesEditaveis(false);
            //desabilito a edicao do formulario
            this.habilitarCampos(false);
            //metodo utilizado para tratar os eventos de tab no campo de texto
            this.txtCNPJ.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
            this.txtCNPJ.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("###.###.###-##")));
        } catch (ParseException ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaCliente.class, "public TelaCliente()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
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
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaCliente.class, "private void botoesEditaveis()");
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
                this.txtNomeCliente.setEnabled(habilitar);
                this.txtCNPJ.setEnabled(habilitar);
                this.txtCNH.setEnabled(habilitar);
                this.txtCelular.setEnabled(habilitar);
                this.txtEndereco.setEnabled(habilitar);
                this.txtIERG.setEnabled(habilitar);
                this.txtTelefone.setEnabled(habilitar);
                this.comboTipoCliente.setEnabled(habilitar);
                this.txtNomeCliente.requestFocus();
            } else {
                this.txtNomeCliente.setEnabled(habilitar);
                this.txtCNPJ.setEnabled(habilitar);
                this.txtCNH.setEnabled(habilitar);
                this.txtCelular.setEnabled(habilitar);
                this.txtEndereco.setEnabled(habilitar);
                this.txtIERG.setEnabled(habilitar);
                this.txtTelefone.setEnabled(habilitar);
                this.comboTipoCliente.setEnabled(habilitar);
                this.txtCelular.requestFocus();
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaCliente.class, "private void habilitarCampos()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    /**
     * Método responsavel por validar se todos os campos do formulario foram
     * preenchidos
     *
     * @return um dado booleano sendo verdadeiro se todos os dados estao
     * preenchidos e false se o mesmo nao estiver
     */
    private boolean validarInformacao() {
        try {
            // se o campo codigo  for vazio
            if (txtNomeCliente.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe o nome do cliente.");
                this.txtNomeCliente.requestFocus();
                return false;
                // se o campo marca  for vazio    
            } else if (txtCNH.getText().isEmpty() && comboTipoCliente.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Informe a CNH do motorista.");
                this.txtCNH.requestFocus();
                return false;
                // se o campo modelo for vazio   
            } else if (txtIERG.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe o IE/RG do cliente.");
                this.txtIERG.requestFocus();
                return false;
            } else if (txtEndereco.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe o  endereço do cliente.");
                this.txtEndereco.requestFocus();
                return false;
            } else if (txtCNPJ.getText().equals("  .   .   /    -  ") || txtCNPJ.getText().equals("   .   .   -  ")) {
                JOptionPane.showMessageDialog(this, "Informe o CNPJ/CPF do cliente.");
                this.txtCNPJ.requestFocus();
                return false;
            } else if (txtTelefone.getText().equals("(  )    -    ")) {
                JOptionPane.showMessageDialog(this, "Informe o telefone do cliente.");
                this.txtTelefone.requestFocus();
                return false;
            } else if (txtCelular.getText().equals("(  )    -    ")) {
                JOptionPane.showMessageDialog(this, "Informe o celular do cliente.");
                this.txtCelular.requestFocus();
                return false;
            } else if ((txtCNPJ.getText().trim().length() < 14) && (comboTipoCliente.getSelectedIndex() == 0)) {
                JOptionPane.showMessageDialog(this, "Informe o CNPJ/CPF do cliente.");
                this.txtCNPJ.requestFocus();
                return false;
            } else if ((txtCNPJ.getText().trim().length() < 18) && (comboTipoCliente.getSelectedIndex() == 1)) {
                JOptionPane.showMessageDialog(this, "Informe o CNPJ/CPF do cliente.");
                this.txtCNPJ.requestFocus();
                return false;
            } else if (txtTelefone.getText().trim().length() < 13) {
                JOptionPane.showMessageDialog(this, "Informe o telefone do cliente.");
                this.txtTelefone.requestFocus();
                return false;
            } else if (txtCelular.getText().trim().length() < 13) {
                JOptionPane.showMessageDialog(this, "Informe o celular do cliente.");
                this.txtCelular.requestFocus();
                return false;
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaCliente.class, "private boolean validarInformacao()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
        return true;
    }

    /**
     * Metodo responsavel por receber um objeto veiculo selecionado pelo usuario
     * na tela de consulta
     *
     * @param cliente
     */
    public void receberCliente(Cliente cliente) {
        try {
            this.cliente = cliente;
            if (this.cliente.getTipo() == 'F') {
                this.comboTipoCliente.setSelectedIndex(0);
            } else {
                this.comboTipoCliente.setSelectedIndex(1);
            }
            this.txtCodigoCliente.setText(String.valueOf(this.cliente.getCodigo()));
            this.txtCNPJ.setText(this.cliente.getCpf());
            this.txtIERG.setText(this.cliente.getRg());
            this.txtCNH.setText(this.cliente.getCnh());
            this.txtEndereco.setText(this.cliente.getEndereco());
            this.txtNomeCliente.setText(this.cliente.getNome());
            this.txtCelular.setText(this.cliente.getCelular());
            this.txtTelefone.setText(this.cliente.getTelefone());
            this.btnSalvar.setEnabled(true);
            this.btnAltera.setEnabled(true);
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaCliente.class, "public void receberCliente()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    /**
     * metodo responsavel por carregar os dados contidos no formulario para um
     * objeto do tipo cliente
     */
    private void configurarCliente() {
        try {
            this.cliente.setCelular(txtCelular.getText());
            this.cliente.setCnh(txtCNH.getText());
            if (!txtCodigoCliente.getText().isEmpty()) {
                this.cliente.setCodigo(Integer.parseInt(txtCodigoCliente.getText()));
            }
            this.cliente.setCpf(txtCNPJ.getText());
            this.cliente.setEndereco(txtEndereco.getText());
            this.cliente.setNome(txtNomeCliente.getText());
            this.cliente.setRg(txtIERG.getText());
            this.cliente.setTelefone(txtTelefone.getText());
            if (comboTipoCliente.getSelectedIndex() == 0) {
                this.cliente.setTipo('F');
            } else {
                this.cliente.setTipo('J');
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaCliente.class, "private void configurarCliente()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    /**
     * metodo responsavel por limpar os campos do formulario de cadastro
     */
    private void limparCampos() {
        try {
            this.txtCodigoCliente.setText("");
            this.txtCNH.setText("");
            this.txtCNPJ.setText("");
            this.txtCNPJ.setValue(null);
            this.txtCelular.setText("");
            this.txtEndereco.setText("");
            this.txtIERG.setText("");
            this.txtNomeCliente.setText("");
            this.txtTelefone.setText("");
            this.repaint();
            this.revalidate();
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaCliente.class, "private void limparCampos()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelFundoCliente = new javax.swing.JPanel();
        ToolBarSuperior = new javax.swing.JToolBar();
        btnAltera = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnPesquisar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        txtCodigoCliente = new javax.swing.JFormattedTextField();
        txtNomeCliente = new javax.swing.JFormattedTextField();
        txtCNPJ = new javax.swing.JFormattedTextField();
        txtIERG = new javax.swing.JFormattedTextField();
        txtCNH = new javax.swing.JFormattedTextField();
        comboTipoCliente = new javax.swing.JComboBox();
        txtEndereco = new javax.swing.JFormattedTextField();
        txtCelular = new javax.swing.JFormattedTextField();
        txtTelefone = new javax.swing.JFormattedTextField();
        lblCodigo = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        lblCNPJ = new javax.swing.JLabel();
        lblIERG = new javax.swing.JLabel();
        lblCNH = new javax.swing.JLabel();
        lblEndereco = new javax.swing.JLabel();
        lblCelular = new javax.swing.JLabel();
        lblTelefone = new javax.swing.JLabel();
        lblImagem = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PARKING MANAGEMENT SYSTEM - Cadastro de Clientes");

        painelFundoCliente.setBackground(new java.awt.Color(255, 255, 255));
        painelFundoCliente.setPreferredSize(new java.awt.Dimension(800, 600));
        painelFundoCliente.setLayout(null);

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

        painelFundoCliente.add(ToolBarSuperior);
        ToolBarSuperior.setBounds(0, 0, 550, 85);

        txtCodigoCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigoCliente.setEnabled(false);
        painelFundoCliente.add(txtCodigoCliente);
        txtCodigoCliente.setBounds(10, 110, 68, 20);
        painelFundoCliente.add(txtNomeCliente);
        txtNomeCliente.setBounds(80, 110, 259, 20);

        try {
            txtCNPJ.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCNPJ.setText("");
        txtCNPJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCNPJKeyPressed(evt);
            }
        });
        painelFundoCliente.add(txtCNPJ);
        txtCNPJ.setBounds(10, 160, 125, 20);

        txtIERG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIERGKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIERGKeyTyped(evt);
            }
        });
        painelFundoCliente.add(txtIERG);
        txtIERG.setBounds(150, 160, 147, 20);

        txtCNH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCNHKeyTyped(evt);
            }
        });
        painelFundoCliente.add(txtCNH);
        txtCNH.setBounds(310, 160, 130, 20);

        comboTipoCliente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        comboTipoCliente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Física", "Jurídica" }));
        comboTipoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoClienteActionPerformed(evt);
            }
        });
        painelFundoCliente.add(comboTipoCliente);
        comboTipoCliente.setBounds(340, 110, 100, 20);
        painelFundoCliente.add(txtEndereco);
        txtEndereco.setBounds(10, 210, 360, 20);

        try {
            txtCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        painelFundoCliente.add(txtCelular);
        txtCelular.setBounds(10, 260, 199, 20);

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        painelFundoCliente.add(txtTelefone);
        txtTelefone.setBounds(220, 260, 150, 20);

        lblCodigo.setBackground(new java.awt.Color(255, 255, 255));
        lblCodigo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCodigo.setText("Código");
        painelFundoCliente.add(lblCodigo);
        lblCodigo.setBounds(10, 90, 68, 14);

        lblNome.setBackground(new java.awt.Color(255, 255, 255));
        lblNome.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblNome.setText(" Razão Social/Nome Cliente");
        painelFundoCliente.add(lblNome);
        lblNome.setBounds(80, 90, 245, 14);

        lblTipo.setBackground(new java.awt.Color(255, 255, 255));
        lblTipo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblTipo.setText("Tipo Pessoa");
        painelFundoCliente.add(lblTipo);
        lblTipo.setBounds(340, 90, 117, 14);

        lblCNPJ.setBackground(new java.awt.Color(255, 255, 255));
        lblCNPJ.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCNPJ.setText("CNPJ/CPF");
        painelFundoCliente.add(lblCNPJ);
        lblCNPJ.setBounds(10, 140, 116, 14);

        lblIERG.setBackground(new java.awt.Color(255, 255, 255));
        lblIERG.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblIERG.setText("IE/RG");
        painelFundoCliente.add(lblIERG);
        lblIERG.setBounds(150, 140, 116, 14);

        lblCNH.setBackground(new java.awt.Color(255, 255, 255));
        lblCNH.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCNH.setText("CNH");
        painelFundoCliente.add(lblCNH);
        lblCNH.setBounds(310, 140, 116, 14);

        lblEndereco.setBackground(new java.awt.Color(255, 255, 255));
        lblEndereco.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblEndereco.setText("Endereço");
        painelFundoCliente.add(lblEndereco);
        lblEndereco.setBounds(10, 190, 199, 14);

        lblCelular.setBackground(new java.awt.Color(255, 255, 255));
        lblCelular.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCelular.setText("Celular");
        painelFundoCliente.add(lblCelular);
        lblCelular.setBounds(10, 240, 199, 14);

        lblTelefone.setBackground(new java.awt.Color(255, 255, 255));
        lblTelefone.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblTelefone.setText("Telefone ");
        painelFundoCliente.add(lblTelefone);
        lblTelefone.setBounds(220, 240, 199, 14);

        lblImagem.setBackground(new java.awt.Color(255, 255, 255));
        lblImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/parking/imagens/clientes.png"))); // NOI18N
        painelFundoCliente.add(lblImagem);
        lblImagem.setBounds(420, 200, 90, 90);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelFundoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelFundoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAlteraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlteraActionPerformed
        try {
            // se o campo de texto for vazio
            if (this.txtCodigoCliente.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Não é permitido alterar um registro não salvo!");
                this.habilitarCampos(false);
                this.botoesEditaveis(false);
            } else {
                this.habilitarCampos(true);
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaCliente.class, " private void btnAlteraActionPerformed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }//GEN-LAST:event_btnAlteraActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        try {
            if (!txtCodigoCliente.getText().isEmpty()) {
                int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente iniciar um novo cadastro ?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    //habilito os campos do formulario
                    this.habilitarCampos(true);
                    //habilito os botoes da paleta de cadastro
                    this.botoesEditaveis(true);
                    //limpo os campso
                    this.limparCampos();
                }
            } else {
                //habilito os campos do formulario
                this.habilitarCampos(true);
                //habilito os botoes da paleta de cadastro
                this.botoesEditaveis(true);
                //limpo os campso
                this.limparCampos();
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaCliente.class, "private void btnNovoActionPerformed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        try {
            //instancio a tela de cadastro de clientes
            this.telaConsultaCliente = new TelaConsultaCliente(telaPrincipal, true, persistencia, this);
            //centralizo a exibicao do componente no JFrame
            this.telaConsultaCliente.setLocationRelativeTo(null);
            //torno visivel a tela de cadastro de clientes
            this.telaConsultaCliente.setVisible(true);
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaCliente.class, "private void btnPesquisarActionPerformed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            if (validarInformacao()) {
                this.configurarCliente();
                if (txtCodigoCliente.getText().isEmpty()) {
                    if (txtCNPJ.getText().equals("  .   .   /    -  ") || txtCNPJ.getText().equals("   .   .   -  ")) {
                        JOptionPane.showMessageDialog(this, "Informe o CNPJ/CPF do cliente.");
                        this.txtCNPJ.requestFocus();
                    } else if ((txtCNPJ.getText().trim().length() < 14) && (comboTipoCliente.getSelectedIndex() == 0)) {
                        JOptionPane.showMessageDialog(this, "CNPJ/CPF informado é incorreto.");
                        this.txtCNPJ.requestFocus();
                    } else if ((txtCNPJ.getText().trim().length() < 18) && (comboTipoCliente.getSelectedIndex() == 1)) {
                        JOptionPane.showMessageDialog(this, "CNPJ/CPF informado é incorreto.");
                        this.txtCNPJ.requestFocus();
                    } else {
                        Cliente c = persistencia.retornarClienteCadastrado(this.txtCNPJ.getText().trim());
                        if (c == null) {
                            boolean retorno;
                            if (txtCNPJ.getText().length() == 18) {
                                retorno = Validador.isCNPJ(txtCNPJ.getText().replace(".", "").replace("/", "").replace("-", "").trim());
                                if (retorno) {
                                    persistencia.inserirObjeto(cliente);
                                    JOptionPane.showMessageDialog(this, "Registro inserido com sucesso!");
                                    this.limparCampos();
                                    this.botoesEditaveis(false);
                                    this.habilitarCampos(false);
                                } else {
                                    JOptionPane.showMessageDialog(this, "O CNPJ/CPF informado é inválido");
                                    txtCNPJ.requestFocus();
                                }
                            } else if (txtCNPJ.getText().length() == 14) {
                                retorno = Validador.isCPF(txtCNPJ.getText().replace(".", "").replace("-", "").trim());
                                if (retorno) {
                                    persistencia.inserirObjeto(cliente);
                                    JOptionPane.showMessageDialog(this, "Registro inserido com sucesso!");
                                    this.limparCampos();
                                    this.botoesEditaveis(false);
                                    this.habilitarCampos(false);
                                } else {
                                    JOptionPane.showMessageDialog(this, "O CNPJ/CPF informado é inválido");
                                    txtCNPJ.requestFocus();
                                }
                            }
                        } else {
                            int resposta = JOptionPane.showConfirmDialog(this, "Cliente já cadastrado. Deseja Visualizá-lo?", "Confirmação", JOptionPane.YES_NO_OPTION);
                            if (resposta == JOptionPane.YES_OPTION) {
                                receberCliente(c);
                            }
                        }
                    }
                } else {
                    persistencia.atualizarObjeto(cliente);
                    JOptionPane.showMessageDialog(this, "Registro atualizado com sucesso!");
                    this.limparCampos();
                    this.botoesEditaveis(false);
                    this.habilitarCampos(false);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaCliente.class, "private void btnSalvarActionPerformed()");
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
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaCliente.class, "private void btnSairActionPerformed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }//GEN-LAST:event_btnSairActionPerformed

    private void comboTipoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoClienteActionPerformed
        try {
            if (comboTipoCliente.getSelectedIndex() == 0) {
                this.txtCNPJ.setText("");
                this.txtCNPJ.setValue(null);
                this.txtCNPJ.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("###.###.###-##")));
            } else {
                this.txtCNPJ.setText("");
                this.txtCNPJ.setValue(null);
                this.txtCNPJ.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##.###.###/####-##")));
            }
            this.txtCNPJ.requestFocus();
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaCliente.class, "private void comboTipoClienteActionPerformed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }//GEN-LAST:event_comboTipoClienteActionPerformed

    private void txtIERGKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIERGKeyTyped
        try {
            String numeros = "0123456789";
            if (!numeros.contains(String.valueOf(evt.getKeyChar()))) {
                evt.consume();
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaCliente.class, "private void txtIERGKeyTyped()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }//GEN-LAST:event_txtIERGKeyTyped

    private void txtCNHKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCNHKeyTyped
        try {
            String numeros = "0123456789";
            if (!numeros.contains(String.valueOf(evt.getKeyChar()))) {
                evt.consume();
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaCliente.class, "private void txtCNHKeyTyped()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }//GEN-LAST:event_txtCNHKeyTyped

    private void txtCNPJKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCNPJKeyPressed
        try {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                if (txtCNPJ.getText().equals("  .   .   /    -  ") || txtCNPJ.getText().equals("   .   .   -  ")) {
                    JOptionPane.showMessageDialog(this, "Informe o CNPJ/CPF do cliente.");
                    this.txtCNPJ.requestFocus();
                } else if ((txtCNPJ.getText().trim().length() < 14) && (comboTipoCliente.getSelectedIndex() == 0)) {
                    JOptionPane.showMessageDialog(this, "CNPJ/CPF informado é incorreto.");
                    this.txtCNPJ.requestFocus();
                } else if ((txtCNPJ.getText().trim().length() < 18) && (comboTipoCliente.getSelectedIndex() == 1)) {
                    JOptionPane.showMessageDialog(this, "CNPJ/CPF informado é incorreto.");
                    this.txtCNPJ.requestFocus();
                } else {
                    Cliente c = persistencia.retornarClienteCadastrado(this.txtCNPJ.getText().trim());
                    if (c == null) {
                        boolean retorno;
                        if (txtCNPJ.getText().length() == 18) {
                            retorno = Validador.isCNPJ(txtCNPJ.getText().replace(".", "").replace("/", "").replace("-", ""));
                            if (retorno) {
                                txtIERG.requestFocus();
                            } else {
                                JOptionPane.showMessageDialog(this, "O CNPJ/CPF informado é inválido");
                                txtCNPJ.requestFocus();
                            }
                        } else if (txtCNPJ.getText().length() == 14) {
                            retorno = Validador.isCPF(txtCNPJ.getText().replace(".", "").replace("-", ""));
                            if (retorno) {
                                txtIERG.requestFocus();
                            } else {
                                JOptionPane.showMessageDialog(this, "O CNPJ/CPF informado é inválido");
                                txtCNPJ.requestFocus();
                            }
                        }
                    } else {
                        int resposta = JOptionPane.showConfirmDialog(this, "Cliente já cadastrado. Deseja Visualizá-lo?", "Confirmação", JOptionPane.YES_NO_OPTION);
                        if (resposta == JOptionPane.YES_OPTION) {
                            receberCliente(c);
                        }
                    }
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_TAB) {
                if (txtCNPJ.getText().equals("  .   .   /    -  ") || txtCNPJ.getText().equals("   .   .   -  ")) {
                    JOptionPane.showMessageDialog(this, "Informe o CNPJ/CPF do cliente.");
                    this.txtCNPJ.requestFocus();
                } else if ((txtCNPJ.getText().trim().length() < 14) && (comboTipoCliente.getSelectedIndex() == 0)) {
                    JOptionPane.showMessageDialog(this, "CNPJ/CPF informado é incorreto.");
                    this.txtCNPJ.requestFocus();
                } else if ((txtCNPJ.getText().trim().length() < 18) && (comboTipoCliente.getSelectedIndex() == 1)) {
                    JOptionPane.showMessageDialog(this, "CNPJ/CPF informado é incorreto.");
                    this.txtCNPJ.requestFocus();
                } else {
                    Cliente c = persistencia.retornarClienteCadastrado(this.txtCNPJ.getText().trim());
                    if (c == null) {
                        boolean retorno;
                        if (txtCNPJ.getText().length() == 18) {
                            retorno = Validador.isCNPJ(txtCNPJ.getText().replace(".", "").replace("/", "").replace("-", ""));
                            if (retorno) {
                                txtIERG.requestFocus();
                            } else {
                                JOptionPane.showMessageDialog(this, "O CNPJ/CPF informado é inválido");
                                txtCNPJ.requestFocus();
                            }
                        } else if (txtCNPJ.getText().length() == 14) {
                            retorno = Validador.isCPF(txtCNPJ.getText().replace(".", "").replace("-", ""));
                            if (retorno) {
                                txtIERG.requestFocus();
                            } else {
                                JOptionPane.showMessageDialog(this, "O CNPJ/CPF informado é inválido");
                                txtCNPJ.requestFocus();
                            }
                        }
                    } else {
                        int resposta = JOptionPane.showConfirmDialog(this, "Cliente já cadastrado. Deseja Visualizá-lo?", "Confirmação", JOptionPane.YES_NO_OPTION);
                        if (resposta == JOptionPane.YES_OPTION) {
                            receberCliente(c);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaCliente.class, " private void txtCNPJKeyPressed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        }
    }//GEN-LAST:event_txtCNPJKeyPressed

    private void txtIERGKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIERGKeyPressed
        try {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                txtCNH.requestFocus();
            } else if (evt.getKeyCode() == KeyEvent.VK_TAB) {
                txtCNH.requestFocus();
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaCliente.class, "private void txtIERGKeyPressed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        }
    }//GEN-LAST:event_txtIERGKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar ToolBarSuperior;
    private javax.swing.JButton btnAltera;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox comboTipoCliente;
    private javax.swing.JLabel lblCNH;
    private javax.swing.JLabel lblCNPJ;
    private javax.swing.JLabel lblCelular;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblEndereco;
    private javax.swing.JLabel lblIERG;
    private javax.swing.JLabel lblImagem;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JPanel painelFundoCliente;
    private javax.swing.JFormattedTextField txtCNH;
    private javax.swing.JFormattedTextField txtCNPJ;
    private javax.swing.JFormattedTextField txtCelular;
    private javax.swing.JFormattedTextField txtCodigoCliente;
    private javax.swing.JFormattedTextField txtEndereco;
    private javax.swing.JFormattedTextField txtIERG;
    private javax.swing.JFormattedTextField txtNomeCliente;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
