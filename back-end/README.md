# A

Primeira Forma Normal (1FN): Cada tabela tem uma chave primária (id em Movies e Genres, e a combinação de movie_id e genre_id em MovieGenres). Todas as outras colunas em cada tabela contêm valores atômicos (indivisíveis).

Segunda Forma Normal (2FN): Todas as colunas não-chave em cada tabela são dependentes da chave primária inteira. Isso é trivialmente verdadeiro para Movies e Genres porque eles têm apenas uma coluna em sua chave primária. Para MovieGenres, não existem colunas não-chave.

Terceira Forma Normal (3FN): Todas as colunas não-chave em cada tabela são diretamente dependentes da chave primária e não de outras colunas não-chave. Novamente, isso é trivialmente verdadeiro porque não existem colunas não-chave em MovieGenres, e não existem dependências transitivas em Movies ou Genres.