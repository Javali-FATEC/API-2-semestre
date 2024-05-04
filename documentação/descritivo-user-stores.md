**Sprint 3:**
Gerar relatório para se plotar um gráfico boxplot:**
Esse relatório deve retornar os valores numéricos que compoem um gráfico boxplot, o gráfico não será plotada na aplicação, apenas os dados devem ser exibidos utilizando uma estrutura de tabela.
O gráfico boxplot é composto por seis elementos: limite inferior, quartil 1, mediana (quartil 2), quartil 3, limite superior e outliers. Todos esses elementos devem ser retornados nesse relatório. Segue um descritivo de cada um dos valores:
- Limite Inferior: Esse é o menor valor registrado que não seja um outliers.
- Limite Superior: Esse é o maior valor registrado que não seja um outliers.
	- Outliers: São valores que fogem dos limites teóricos que são dados a partir da seguinte formula
		-   Limite teorico superior 𝑄1−1,5×𝐴𝐼𝑄
		-   Limite teorico inferior 𝑄3−1,5×𝐴𝐼𝑄
- Quaris:
	- Quatil 1:   Valor que está posição que equivale 25% do tamanho conjunto analisado.
	- Mediana (Quatil 2):   Valor que está posição que equivale 50% do tamanho conjunto analisado.
	- Quatil 3:   Valor que está posição que equivale 75% do tamanho conjunto analisado.
Esse relatório deve ser gerado a partir da seleção de uma estação e uma data especifica. 
Não se deve listar todas as estações, deve ser selecionada uma cidade e assim disponibilizar as estações para seleção do usuário.

**Gerenciar valores limites a serem utilizados como parametro para definir valores de risco nas medições**
Deve ser possível definir dois valores para cada métrica presente no sistema, um deles referente a um valor máximo e um valor minimo a serem interpretados pelo sistema, de maneira que caso ocorra uma entrada que extrapole um dos valores, seja informado ao usuário no momento da importação do arquivo CSV que contem as medições.
Deve ser disponibilzado ao pesquisador, uma tela onde é possível configurar um tipo de metrica, habilitar ou desabilitar o recurso e definir um valor máximo e minimo, como também salvar ou editar essa configuração.