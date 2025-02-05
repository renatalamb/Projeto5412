✒️ Autor do Read.me: Daniel Mack

📔Titulo: Gestão de uma confeitaria

📝Descrição do projecto:

Este projeto tem como objetivo desenvolver um sistema de gestão para uma confeitaria, facilitando o controle de vendas, estoque, e atendimento ao cliente. Através de uma interface intuitiva, o sistema permitirá que os funcionários gerenciem pedidos, acompanhem o inventário de ingredientes e analisem o desempenho das vendas.
_________________________________________
📋Pre requisitos:

👨‍💻Sistema Operacional:

🪟Windows: Windows 10 ou superior.
🍎macOS: macOS 10.14 ou superior.
🐧Linux: Distribuições modernas de Linux (como Ubuntu, Fedora, etc.).

🎚️Hardware:
Processador: CPU com suporte a 64 bits.
Memória RAM: Mínimo de 4 GB (8 GB ou mais recomendado para projetos maiores).
Espaço em Disco: Pelo menos 1 GB de espaço livre em disco, mais espaço adicional para projetos e plugins.

☕Java:
O IntelliJ IDEA é uma IDE Java, então você precisará do Java Development Kit (JDK) instalado. A versão recomendada do JDK pode variar, mas geralmente é recomendável usar a versão mais recente do JDK 11 ou superior.

🖥️Resolução de Tela:
Resolução mínima de 1024x768, mas uma resolução maior é recomendada para melhor experiência.

🛜Conexão com a Internet:
Para baixar plugins, atualizações e acessar recursos online.
__________________________________________
🔧 Desenvolvimento:

Passo 1: Instalar o JDK (Java Development Kit)

1: Baixar o JDK(Java Development Kit)
Acesse o site oficial da Oracle ou o site do OpenJDK.

Escolha a versão do JDK que deseja instalar (recomenda-se a versão mais recente).

2: Instalar o JDK:
Execute o instalador que você baixou e siga as instruções na tela.

Durante a instalação, anote o caminho de instalação do JDK, pois você pode precisar dele mais tarde.

3: Configurar a variável de ambiente JAVA_HOME (opcional, mas recomendado):

🪟Windows:
Clique com o botão direito em "Este PC" ou "Meu Computador" e selecione "Propriedades".

Clique em "Configurações avançadas do sistema".
Na aba "Avançado", clique em "Variáveis de Ambiente".

Em "Variáveis do sistema", clique em "Novo" e adicione:
Nome da variável: JAVA_HOME
Valor da variável: o caminho onde o JDK foi instalado (ex: C:\Program Files\Java\jdk-17).

🍎macOS/🐧Linux:
Abra o terminal e adicione a seguinte linha ao seu arquivo de configuração de shell (ex: ~/.bash_profile, ~/.bashrc, ou ~/.zshrc):

export JAVA_HOME=/caminho/para/o/jdk

Salve o arquivo e execute source ~/.bash_profile (ou o arquivo correspondente) para aplicar as mudanças.

/////////////////////////

Passo 2: Baixar e Instalar o IntelliJ IDEA

1: Baixar o IntelliJ IDEA:
Acesse o site oficial do JetBrains IntelliJ IDEA.

Escolha a versão Community (gratuita) ou a versão Ultimate (paga) e faça o download.



2: Instalar o IntelliJ IDEA:
Execute o instalador e siga as instruções na tela.
Durante a instalação, você pode escolher criar um atalho na área de trabalho e associar arquivos .java ao IntelliJ.
//////////////////////////
Passo 3: Configurar o IntelliJ IDEA

1:Iniciar o IntelliJ IDEA:
Abra o IntelliJ IDEA após a instalação.

2:Configurar o JDK no IntelliJ:
Na tela inicial, clique em "Configure" e depois em "Project Defaults" > "Project Structure".
Na aba "SDKs", clique no ícone de "+" e selecione "JDK".
Navegue até o diretório onde o JDK foi instalado e selecione-o.

3:Criar um novo projeto
Na tela inicial, clique em "New Project".
Selecione "Java" e clique em "Next".
Escolha o JDK que você configurou anteriormente e clique em "Next".
Dê um nome ao seu projeto e escolha o local onde deseja salvá-lo. Clique em "Finish".


//////////////////////
Passo 4: Escrever e Executar um Programa Java

1:Criar uma nova classe Java
Clique com o botão direito na pasta src do seu projeto, selecione "New" > "Java Class".
Dê um nome à sua classe (ex: Main) e clique em "OK".

2:Escrever o código
No editor, escreva um simples programa Java: 

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}

3:Executar o programa:
Clique com o botão direito na classe que você criou e selecione "Run 'Main.main()'".
O resultado será exibido na janela de saída na parte inferior do IntelliJ.

////////////////////////////////

Passo 5: Instalar Plugins (opcional)

Você pode instalar plugins adicionais para melhorar sua experiência de desenvolvimento. Vá em "File" > "Settings" > "Plugins" e explore as opções disponíveis.

_________________________________________
⚙️Como executar testes para o sistema:

1.Configurar o Ambiente:
Certifique-se de que o IntelliJ IDEA está instalado e configurado corretamente.
Abra seu projeto Java no IntelliJ.



2.Adicionar Dependências de Teste:
Se você estiver usando o Maven, adicione as dependências de teste no seu arquivo pom.xml. Por exemplo, para JUnit:

<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13.2</version>
    <scope>test</scope>
</dependency>

Se estiver usando Gradle, adicione ao seu arquivo build.gradle:
testImplementation 'junit:junit:4.13.2'

Criar uma Classe de Teste:
Crie uma nova classe de teste. Clique com o botão direito na pasta src/test/java e selecione New > Java Class.
Nomeie a classe de teste, por exemplo, MyClassTest.



4.Escrever Testes:
Use anotações do JUnit para escrever seus testes. Por exemplo: 

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MyClassTest {
    @Test
    public void testMyMethod() {
        MyClass myClass = new MyClass();
        assertEquals("Expected result", myClass.myMethod());
    }
}



5.Executar os Testes:
Clique com o botão direito na classe de teste ou no método de teste e selecione Run 'MyClassTest' ou Run 'testMyMethod'.
Você também pode executar todos os testes do projeto clicando com o botão direito na pasta test e selecionando Run 'All Tests'.


6.Verificar Resultados:
Após a execução, verifique a janela de resultados de teste na parte inferior do IntelliJ para ver quais testes passaram ou falharam.


7.Depuração (Opcional):
Se um teste falhar, você pode depurá-lo clicando com o botão direito e selecionando Debug 'MyClassTest'.

_________________________________________

🔩 Os testes são verificados por várias razões importantes:

1.Precisão: Para garantir que os resultados sejam corretos e reflitam a realidade.

2.Confiabilidade: Para assegurar que os testes produzam resultados consistentes ao longo do tempo.

3.Validade: Para confirmar que os testes medem o que realmente se propõem a medir.

4.Identificação de Erros: Para encontrar e corrigir possíveis erros no processo de teste.

5.Transparência: Para aumentar a confiança entre todos os envolvidos, como alunos e formadores.

6.Regulamentação: Para cumprir normas e exigências legais em diferentes setores.

__________________________________________

⌨️ E testes de estilo de codificação
Explique que eles verificam esses testes e porque.

__________________________________________

📦 Implantação:

Intellij - Ferramenta de codigo (https://www.jetbrains.com/idea/)

Gui Designer - Ferramenta de interface graphica
(https://www.jetbrains.com/help/idea/design-gui-using-swing.html)

__________________________________________

🛠️ Construído com:

🪟Windows11

IntelliJ IDEA(Community edition V.231.8109.175)

Formato: ☕Java
__________________________________________

🖇️ Membros do Projecto
Renata,Mariana,Samara,Daniel
__________________________________________

📌 Versão
Nós usamos SemVer para controle de versão. Para as versões disponíveis, observe as tags neste repositório.
__________________________________________
