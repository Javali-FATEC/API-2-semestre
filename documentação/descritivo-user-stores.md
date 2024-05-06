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