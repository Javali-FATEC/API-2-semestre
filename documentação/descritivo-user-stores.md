**Sprint 2:**
**Gerar relatórios de valor médio das variáveis climáticas por cidade.**
Esse relatório deve ser possível selecionar uma cidade e um intervalo de tempo, sendo o intervalo de tempo definido por uma data inicial e uma data final. Onde é retornado um valor por data e hora contendo a média de todas as incidências de cada variável climática.
Devem ser retornados ao usuário os dados trazendo a data e hora, a média da variável climática, o nome da variável climática e sua respectiva unidade de medida.

**Gerar relatório de situação.**
Esse relatório deve ser possível selecionar uma cidade e retornar a média de todas as últimas incidências de cada variável climática. Os valores usados para calcular essa média são todos os últimos registros de cada uma das estações da cidade selecionada.

**Sprint 3:**
**Gerar relatório para se plotar um gráfico boxplot:**
Esse relatório deve retornar os valores numéricos que compõem um gráfico boxplot. O gráfico não será plotado na aplicação, apenas os dados devem ser exibidos utilizando uma estrutura de tabela.
O gráfico boxplot é composto por seis elementos: limite inferior, quartil 1, mediana (quartil 2), quartil 3, limite superior e outliers. Todos esses elementos devem ser retornados nesse relatório. Segue um descritivo de cada um dos valores:
- Limite Inferior: Esse é o menor valor registrado que não seja um outliers.
- Limite Superior: Esse é o maior valor registrado que não seja um outliers.
    - Outliers: São valores que fogem dos limites teóricos que são dados a partir da seguinte fórmula.
        -   Limite teórico superior 𝑄1−1,5×𝐴𝐼𝑄
        -   Limite teórico inferior 𝑄3−1,5×𝐴𝐼𝑄
- Quaris:
    - Quatil 1:   Valor que está posição de 25% do tamanho conjunto analisado.
    - Mediana (Quatil 2):   Valor que está posição de  50% do tamanho conjunto analisado.
    - Quatil 3:   Valor que está posição que equivale 75% do tamanho conjunto analisado.
Esse relatório deve ser gerado a partir da seleção de uma estação e uma data específica. 
Não se deve listar todas as estações, deve ser selecionada uma cidade e assim disponibilizar as estações para seleção do usuário.

**Gerenciar valores limites a serem utilizados como parâmetro para definir valores de risco nas medições.**
Deve ser possível definir dois valores para cada métrica presente no sistema, um deles referente a um valor máximo e um valor mínimo a serem interpretados pelo sistema, de maneira que caso ocorra uma entrada que extrapole um dos valores, seja informado ao usuário no momento da importação do arquivo CSV que contem as medições.
Deve ser disponibilizado ao pesquisador uma tela onde é possível configurar um tipo de métrica, habilitar ou desabilitar o recurso e definir um valor máximo e mínimo, como também salvar ou editar essa configuração.

**Sprint 4:**
**Alterar valores de risco nas medições**
Deve ser possível alterar os valores entendidos como de risco, uma vez que identificado um valor de risco no momento da importação e antes de salvar os dados importados.

- [ ] Deve ser incluído um botão editar na tela de visualizar Valores de Risco, o botão deve estar em uma terceira coluna.
- [ ] Ao clicar no botão, deve ser aberta uma tela onde deve ser possível inserir um novo valor numérico.
- [ ] Deve haver nessa tela mencionada no tópico anterior, um botão de salvar onde será possível alterar o valor presente nos dados importados. Observação: Ao clicar em salvar, os valores não devem ser salvos na base de dados, mas sim substituir os valores entendidos como de risco importados.
- [ ] Não deve ser possível inserir um valor que não seja do tipo numérico com ponto flutuante e nem um valor vazio (o valor zero deve ser aceito)

**Gerir as cidades e as estações, tal como a relação entre estação e cidade.**
Deve ser possível ao usuário editar as informações de uma cidade ou de uma estação, como também excluir uma cidade ou estação.

- [ ] Deve ser criado um novo menu na tela principal esse menu terá o texto "Gerenciar" caso o mesmo ainda não exista

- [ ] Deve ser criado um submenu com o texto "Cidade"
- [ ] Uma tela deve ser aberta ao clicar em Cidade, nesta deve ser possível selecionar uma cidade entre todas as cidades presentes no sistema.
- [ ] Ao selecionar uma cidade cadastrada no sistema, deve ser possível editar ou cadastrar seu nome, ou editar sua sigla.
- [ ] Deve ser inserido ao final da tela um botão de salvar, que ao ser clicado deve persistir nas informações alteradas na cidade.
- [ ] Deve ser inserido ao final da tela um botão de excluir, que ao ser clicado deve excluir a cidade selecionada dos registros, porém, caso essa cidade possua alguma estação vinculada a ela, deve ser exibida ao usuário uma mensagem de erro com o seguinte texto: "Impossível excluir cidade, pois há registros vinculados a ela".
- [ ] Deve ser verificado que quando o usuário tentar salvar não seja possível mudar a sigla ou nome de uma cidade para um valor que já esteja cadastrado em outra cidade.
- [ ] Não deve ser permitido salvar a sigla vazia

- [ ] Deve ser criado um submenu com o texto "Estação"
- [ ] Uma tela deve ser aberta ao clicar em Estação, nesta deve ser possível selecionar uma cidade entre todas as cidades presentes no sistema
- [ ] Deve ser listada todas as estações da cidade selecionada anteriormente, sendo possível selecionar uma estação nesta lista.
- [ ] Ao selecionar uma estação cadastrada no sistema deve ser possível editar seu código
- [ ] Deve ser inserido ao final da tela um botão de salvar, que ao ser clicado deve persistir nas informações alteradas na estação.
- [ ] Deve ser inserido ao final da tela um botão de excluir, que ao ser clicado deve excluir a estação selecionado dos registros, porém caso essa estação possua algum registro vinculado a essa estação deve ser exibido uma mensagem de alerta exigindo uma confirmação do usuário, com o seguinte texto: "Há registros vinculados com essa estação deseja mesmo executar a exclusão?", disponibilizar um botão de sim ou botão de não
- [ ] Deve ser verificado que, quando o usuário tentar salvar, não seja possível mudar o código para um valor que já esteja cadastrado em outra estação.
- [ ] Não deve ser permitido salvar o código com um valor vazio

**Gerir as unidades de medida usadas no sistema.**
Deve ser possível gerenciar as unidades de medida presentes no sistema, de maneira que o usuário possa alterar informações vinculadas a unidade de medida.

- [ ] Deve ser criado um novo menu na tela principal esse menu terá o texto "Gerenciar" caso o mesmo ainda não exista

- [ ] Deve ser criado um submenu com o texto "Unidades de Medida"
- [ ] Uma tela deve ser aberta ao clicar em Unidades de Medida, nesta deve ser possível selecionar uma Unidade de Medidas entre todas as Unidades de Medidas presentes no sistema.
- [ ] Deve ser possível cadastrar ou alterar um valor textual referente a uma descrição da unidade de medida
- [ ] Devem ser listadas todas as variáveis climáticas que usam aquela unidade de medida. Observação: Não deve ser permitida a alteração das variáveis climáticas vinculadas somente para exibi-las.
- [ ] Deve ser inserido ao final da tela um botão de salvar.
