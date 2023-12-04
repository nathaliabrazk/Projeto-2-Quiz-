package com.example.quiz

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //etapa1: vincular configurações
    private lateinit var binding: ActivityMainBinding

    //etapa 2: criar matriz de perguntas e de opções
    private val questions = arrayOf(
        "Qual é o banco de dados integrado no Android Studio?",
        "Qual serviço do Firebase é frequentemente utilizado para armazenar e sincronizar dados em tempo real?",
        "Em que ano foi lançado o primeiro Android pela Google?"
    )

    private val options = arrayOf(
        arrayOf("MySQL", "SQLite", "Firebase"),
        arrayOf("Firebase Realtime Database", "Firebase Authentication", "Firebase Cloud Messaging"),
        arrayOf("2010", "2006", "2008")
    )

    private val correctAnswers = arrayOf(1, 0, 2)

    //etapa3 : declarar duas variaveis
    private var currentQuestionIndexedValue = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //etapa 11: chamar o método do displayquestion, entrar no app e 1° visualizar as perguntas
        //etapa 12: chamar o metodo dentro do conjunto no ouvinte de clique
        displayQuestion()

        binding.option1Button.setOnClickListener {
            checkAnswer(0)
        }
        binding.option2Button.setOnClickListener {
            checkAnswer(1)
        }
        binding.option3Button.setOnClickListener {
            checkAnswer(2)
        }
        binding.restartButton.setOnClickListener {
            restartQuiz()
        }
    }
    //etapa 4: criar metodo correto das cores dos botões
    private fun correctButtonColors(buttonIndex: Int) {
        when (buttonIndex) {
            0 -> binding.option1Button.setBackgroundColor(Color.GREEN)
            1 -> binding.option2Button.setBackgroundColor(Color.GREEN)
            2 -> binding.option3Button.setBackgroundColor(Color.GREEN)
        }
    }

    //etapa 5: criar metodo incorreto das cores dos botões
    private fun wrongButtonColors(buttonIndex: Int) {
        when (buttonIndex) {
            0 -> binding.option1Button.setBackgroundColor(Color.RED)
            1 -> binding.option2Button.setBackgroundColor(Color.RED)
            2 -> binding.option3Button.setBackgroundColor(Color.RED)
        }
    }

    //etapa 6 : criar metodo de cores de botão de redefinição
    private fun resetButtonColors() {
        binding.option1Button.setBackgroundColor(Color.rgb(50, 59, 96))
        binding.option2Button.setBackgroundColor(Color.rgb(50, 59, 96))
        binding.option3Button.setBackgroundColor(Color.rgb(50, 59, 96))
    }

    //etapa 7: criar metodo de mostrar resultado
    private fun showResults() {
        Toast.makeText(this, "Sua pontuação: $score foi de ${questions.size}", Toast.LENGTH_LONG).show()
        binding.restartButton.isEnabled = true
    }
    //etapa 8 : criar o método de pergunta de exibição
    private fun displayQuestion() {
        binding.questionText.text = questions[currentQuestionIndexedValue]
        binding.option1Button.text = options[currentQuestionIndexedValue][0]
        binding.option2Button.text = options[currentQuestionIndexedValue][1]
        binding.option3Button.text = options[currentQuestionIndexedValue][2]
        resetButtonColors()
    }

    //passo 9 - verificar se a resposta esta correta ou nao:
    private fun checkAnswer(selectedAnswerIndex: Int) {
        val correctAnswerIndex = correctAnswers[currentQuestionIndexedValue]

        if (selectedAnswerIndex == correctAnswerIndex) {
            score++
            correctButtonColors(selectedAnswerIndex)
        } else {
            wrongButtonColors(selectedAnswerIndex)
            correctButtonColors(correctAnswerIndex)
        }

        if (currentQuestionIndexedValue < questions.size - 1) {
            currentQuestionIndexedValue++
            binding.questionText.postDelayed({ displayQuestion() }, 1000)
        } else {
            showResults()
        }
    }

    //etapa 10: Criar método de teste de reinicialização
    private fun restartQuiz() {
        currentQuestionIndexedValue = 0
        score = 0
        displayQuestion()
        binding.restartButton.isEnabled = false
    }
}
