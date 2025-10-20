// Define os níveis de dificuldade para as formações
enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

// Define a classe para representar um Aluno
data class Aluno(val nome: String)

// Define a classe para representar o conteúdo educacional
data class ConteudoEducacional(val nome: String, val duracao: Int = 60)

// Define a classe principal para a Formação
class Formacao(
    val nome: String,
    val nivel: Nivel,
    // Uma formação é composta por uma lista de conteúdos educacionais
    val conteudos: List<ConteudoEducacional>
) {

    // Lista de alunos matriculados (privada para encapsulamento)
    private val inscritos = mutableListOf<Aluno>()

    /**
     * Matricula um ou mais alunos (usando vararg) na formação.
     * Imprime uma mensagem de confirmação.
     */
    fun matricular(vararg alunos: Aluno) {
        // Adiciona todos os alunos passados como argumento à lista de inscritos
        inscritos.addAll(alunos)
        println("Matrícula realizada com sucesso para: ${alunos.joinToString { it.nome }} na formação $nome.")
    }

    /**
     * Retorna uma lista pública (apenas para leitura) dos alunos inscritos.
     * Usamos .toList() para garantir que a lista interna 'inscritos' não seja modificada externamente.
     */
    fun getInscritos(): List<Aluno> {
        return inscritos.toList()
    }
}

/**
 * Função principal para testar a modelagem.
 */
fun main() {
    // --- 1. Criação dos Objetos ---

    // Criando alguns alunos
    val aluno1 = Aluno("Gabriel")
    val aluno2 = Aluno("Lia")
    val aluno3 = Aluno("Daniela")

    // Criando alguns conteúdos educacionais
    val introKotlin = ConteudoEducacional("Introdução ao Kotlin", 90)
    val pooKotlin = ConteudoEducacional("Programação Orientada a Objetos com Kotlin", 120)
    val corrotinas = ConteudoEducacional("Corrotinas em Kotlin", 180)

    // Agrupando os conteúdos em uma lista
    val conteudosFormacaoKotlin = listOf(introKotlin, pooKotlin, corrotinas)

    // Criando a Formação
    val formacaoKotlin = Formacao(
        nome = "Formação Completa Kotlin Developer",
        nivel = Nivel.INTERMEDIARIO,
        conteudos = conteudosFormacaoKotlin
    )

    // --- 2. Execução dos Comportamentos ---

    // Matriculando alunos
    formacaoKotlin.matricular(aluno1, aluno2) // Matriculando dois alunos de uma vez
    formacaoKotlin.matricular(aluno3)        // Matriculando mais um separadamente

    // --- 3. Verificação (Imprimindo os resultados) ---

    println("\n--- Detalhes da Formação: ${formacaoKotlin.nome} ---")
    println("Nível: ${formacaoKotlin.nivel}")

    println("\nConteúdos da Formação:")
    formacaoKotlin.conteudos.forEach {
        println("- ${it.nome} (Duração: ${it.duracao} min)")
    }

    println("\nAlunos Inscritos:")
    formacaoKotlin.getInscritos().forEach {
        println("- ${it.nome}")
    }

    // Exemplo de uma segunda formação para mostrar a reutilização
    val formacaoLogica = Formacao(
        nome = "Lógica de Programação Essencial",
        nivel = Nivel.BASICO,
        conteudos = listOf(ConteudoEducacional("Algoritmos", 60), ConteudoEducacional("Pensamento Computacional", 45))
    )

    val aluno4 = Aluno("Lucas")
    formacaoLogica.matricular(aluno4)

    println("\n--- Detalhes da Formação: ${formacaoLogica.nome} ---")
    println("Alunos Inscritos: ${formacaoLogica.getInscritos().joinToString { it.nome }}")
}