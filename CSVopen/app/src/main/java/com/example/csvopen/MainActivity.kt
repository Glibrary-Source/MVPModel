package com.example.csvopen

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.opencsv.CSVReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.link).setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://chat.openai.com/chat"))
            startActivity(intent)
        }

        val assetManager = this.assets

        val krInputStream = assetManager.open("korea/교육.csv")
        val jpInputStream = assetManager.open("japan/일본어(교육).csv")


//        val enInputStream = assetManager.open("english/learning_English.csv")
//
//        val enMutableList = mutableListOf<String>()
//        val totalMap = mutableMapOf<String, MutableList<String>>()
//
//        for(i in assetManager.list("english")!!){
//            val enInputStream = assetManager.open("english/$i")
//            val enReader = CSVReader(InputStreamReader(enInputStream))
//            val enContent = enReader.readAll()
//
//            for(content in enContent) {
//                for(string in content) {
//                    enMutableList.add(string)
//                }
//            }
//
//            totalMap[i] = enMutableList
//        }
//
//        Log.d("autocheck", totalMap.toString())
//
//
//        val enReader = CSVReader(InputStreamReader(enInputStream))

        val krReader = CSVReader(InputStreamReader(krInputStream))
        val jpReader = CSVReader(InputStreamReader(jpInputStream))

//        val enContent = enReader.readAll()
        val krContent = krReader.readAll()
        val jpContent = jpReader.readAll()


//        val enMutableList = mutableListOf<String>()
        val krMutableList = mutableListOf<String>()
        val jpMutableList = mutableListOf<String>()

//        for(content in enContent) {
//            for(string in content) {
//                enMutableList.add(string)
//            }
//        }

        for(content in krContent) {
            for(string in content) {
                krMutableList.add(string)
            }
        }

        for(content in jpContent) {
            for(string in content) {
                jpMutableList.add(string)
            }
        }

//        val en = enMutableList.toList()
        val kr = krMutableList.toList()
        val jp = jpMutableList.toList()

        val btnDbUpdate = findViewById<Button>(R.id.csvRead)
        val db = FirebaseFirestore.getInstance()

        btnDbUpdate.setOnClickListener{

            //데이터 수정용
//            val test =
//                mapOf(
//                    "categorylist" to mapOf(
//                        "detailenglish" to
//                                mapOf(
//                                    "travel plan" to mapOf(
//                                        "Korea" to listOf(
//                                            "When is the best time of year to visit Korea?",
//                                            "What are the must-see attractions in Korea?",
//                                            "Can you recommend any good Korean restaurants?",
//                                            "What is the currency exchange rate for Korean won?",
//                                            "Is it easy to get around Korea using public transportation?",
//                                            "Are there any cultural customs or etiquette I should be aware of when visiting Korea?",
//                                            "How do I obtain a Korean visa if necessary?",
//                                            "What is the typical weather like during the time of my planned visit?",
//                                            "What are some popular Korean festivals or events happening during my planned visit?",
//                                            "How much should I budget for my trip to Korea?",
//                                            "What are some recommended accommodations in Korea?",
//                                            "Are there any areas or neighborhoods in Korea to avoid?",
//                                            "How can I learn some basic Korean phrases before my trip?",
//                                            "Are there any travel advisories or safety concerns for travelers in Korea?",
//                                            "How do I navigate the Korean subway system?",
//                                            "Are there any recommended day trips or excursions from Seoul?",
//                                            "What are the best shopping areas in Korea?",
//                                            "How do I book tickets for a K-pop concert or show in Korea?",
//                                            "What are the best options for transportation from the airport to my hotel?",
//                                            "Are there any unique experiences or activities in Korea that I shouldn't miss?"),
//                                        "Southeast Asia" to listOf(
//                                            "When is the best time of year to visit Southeast Asia?",
//                                            "What are the must-see attractions in Southeast Asia?",
//                                            "Can you recommend any good local restaurants or food markets?",
//                                            "What is the currency exchange rate for the countries I will be visiting?",
//                                            "Is it easy to get around Southeast Asia using public transportation?",
//                                            "Are there any cultural customs or etiquette I should be aware of when visiting Southeast Asia?",
//                                            "How do I obtain visas for the countries I plan to visit?",
//                                            "What is the typical weather like during the time of my planned visit?",
//                                            "What are some popular festivals or events happening during my planned visit?",
//                                            "How much should I budget for my trip to Southeast Asia?",
//                                            "What are some recommended accommodations in Southeast Asia?",
//                                            "Are there any areas or neighborhoods in Southeast Asia to avoid?",
//                                            "How can I learn some basic phrases in the local languages before my trip?",
//                                            "Are there any travel advisories or safety concerns for travelers in Southeast Asia?",
//                                            "How do I navigate the local transportation systems in each country?",
//                                            "Are there any recommended day trips or excursions from the cities I will be visiting?",
//                                            "What are the best shopping areas in Southeast Asia?",
//                                            "How do I book tickets for local performances or shows in Southeast Asia?",
//                                            "What are the best options for transportation from the airport to my hotel?",
//                                            "Are there any unique experiences or activities in Southeast Asia that I shouldn't miss?",
//                                        ),
//                                        "America" to listOf(
//                                            "What are some popular tourist attractions in the US that are a must-visit?",
//                                            "How do I apply for a US tourist visa, and what are the requirements?",
//                                            "Are there any specific areas in the US that I should avoid due to safety concerns?",
//                                            "How do I get around the US, and what is the most affordable mode of transportation?",
//                                            "What is the local currency used in the US, and what is the exchange rate?",
//                                            "What is the average temperature in the US during the time of my planned visit?",
//                                            "What are some recommended accommodations in the US, and how do I make reservations?",
//                                            "What are some popular American foods that I should try, and where can I find them?",
//                                            "Are there any unique cultural customs or etiquette that I should be aware of when visiting the US?",
//                                            "What are some popular American festivals or events happening during my planned visit?",
//                                            "How do I navigate the local transportation systems in the cities I will be visiting?",
//                                            "What are some recommended day trips or excursions from the cities I will be visiting?",
//                                            "What is the typical dress code for various events in the US?",
//                                            "Are there any travel advisories or safety concerns for travelers in the US?",
//                                            "How do I obtain travel insurance for my trip to the US?",
//                                            "Are there any restrictions on the types of items that I can bring into the US?",
//                                            "What are some of the best shopping areas in the US, and what can I buy there?",
//                                            "How do I book tickets for shows or events in the US?",
//                                            "What are some free or low-cost activities that I can do in the US?",
//                                            "Are there any unique experiences or activities in the US that I shouldn't miss?"
//                                        ),
//                                        "Europe" to listOf(
//                                            "What are some popular tourist attractions in Europe that are a must-visit?",
//                                            "How do I apply for a Schengen visa, and what are the requirements?",
//                                            "What is the best time of year to visit Europe, and what is the weather like during that time?",
//                                            "How do I get around Europe, and what is the most affordable mode of transportation?",
//                                            "What is the local currency used in Europe, and what is the exchange rate?",
//                                            "What are some recommended accommodations in Europe, and how do I make reservations?",
//                                            "What are some popular European foods that I should try, and where can I find them?",
//                                            "What are some unique cultural customs or etiquette that I should be aware of when visiting Europe?",
//                                            "What are some popular European festivals or events happening during my planned visit?",
//                                            "How do I navigate the local transportation systems in the cities I will be visiting?",
//                                            "What are some recommended day trips or excursions from the cities I will be visiting?",
//                                            "What is the typical dress code for various events in Europe?",
//                                            "Are there any travel advisories or safety concerns for travelers in Europe?",
//                                            "How do I obtain travel insurance for my trip to Europe?",
//                                            "What are some free or low-cost activities that I can do in Europe?",
//                                            "What are some of the best shopping areas in Europe, and what can I buy there?",
//                                            "How do I book tickets for shows or events in Europe?",
//                                            "What are some of the best museums or art galleries to visit in Europe?",
//                                            "How can I learn some basic phrases in the local language before my trip to Europe?",
//                                            "Are there any unique experiences or activities in Europe that I shouldn't miss?",
//                                        ),
//                                        "Mexico" to listOf(
//                                            "What are some popular tourist attractions in Mexico that are a must-visit?",
//                                            "What are some unique cultural experiences that I can have in Mexico?",
//                                            "How do I obtain a tourist visa to travel to Mexico, and what are the requirements?",
//                                            "What is the local currency used in Mexico, and what is the exchange rate?",
//                                            "What is the best time of year to visit Mexico, and what is the weather like during that time?",
//                                            "What are some recommended accommodations in Mexico, and how do I make reservations?",
//                                            "What are some popular Mexican dishes that I should try, and where can I find them?",
//                                            "How do I get around Mexico, and what is the most affordable mode of transportation?",
//                                            "What are some popular Mexican festivals or events happening during my planned visit?",
//                                            "How do I navigate the local transportation systems in the cities I will be visiting?",
//                                            "What are some recommended day trips or excursions from the cities I will be visiting?",
//                                            "What is the typical dress code for various events in Mexico?",
//                                            "Are there any travel advisories or safety concerns for travelers in Mexico?",
//                                            "How do I obtain travel insurance for my trip to Mexico?",
//                                            "What are some free or low-cost activities that I can do in Mexico?",
//                                            "What are some of the best shopping areas in Mexico, and what can I buy there?",
//                                            "How do I book tickets for shows or events in Mexico?",
//                                            "What are some of the best museums or art galleries to visit in Mexico?",
//                                            "How can I learn some basic phrases in Spanish before my trip to Mexico?",
//                                            "Are there any unique experiences or activities in Mexico that I shouldn't miss?",
//                                        ),
//                                    ),
//                                    "learning" to mapOf(
//                                        "Education Planning" to listOf(
//                                            "What are my options for further education, and what are the admission requirements?",
//                                            "How do I choose the right program of study for me?",
//                                            "What is the application process like, and what documents will I need to provide?",
//                                            "What is the typical cost of tuition and other fees, and are there any financial aid options available?",
//                                            "What is the duration of the program, and how many courses will I be required to take?",
//                                            "What is the format of the program, and will I be required to attend classes in person or online?",
//                                            "How is the program structured, and what types of assignments and exams will I need to complete?",
//                                            "What resources are available to help me succeed in the program, such as tutoring or academic advising?",
//                                            "What are the career prospects for graduates of the program, and what types of jobs can I expect to get?",
//                                            "How do I balance work or other commitments with my education?",
//                                            "How can I best prepare for the demands of the program, such as studying or managing my time effectively?",
//                                            "What are the requirements for graduation, and what will I need to do to earn my degree or certificate?",
//                                            "What types of skills and knowledge will I gain from the program, and how will they benefit me in my career?",
//                                            "What types of extracurricular activities or opportunities are available, such as clubs or internships?",
//                                            "What is the reputation of the school or program, and how will it affect my career prospects?",
//                                            "How do I make the most of my time in the program, such as networking with other students or attending career fairs?",
//                                            "What types of support services are available, such as mental health counseling or disability services?",
//                                            "What types of research or projects will I be required to complete as part of the program?",
//                                            "How will I be graded or evaluated in the program, and what is the grading scale?",
//                                            "What are some tips for succeeding in the program, such as staying organized or asking for help when needed?",
//                                        ),
//                                        "Foreign Language" to listOf(
//                                            "What are some effective strategies for learning a new language?",
//                                            "What are the most important grammar rules I need to know?",
//                                            "What are some common vocabulary words I should focus on learning?",
//                                            "How can I improve my pronunciation in the new language?",
//                                            "What are some ways to practice listening comprehension in the new language?",
//                                            "How can I improve my reading skills in the new language?",
//                                            "What are some tips for building my speaking skills in the new language?",
//                                            "What are some effective ways to memorize new vocabulary words?",
//                                            "How can I use technology to help me learn the new language?",
//                                            "What are some common mistakes language learners make, and how can I avoid them?",
//                                            "What are some good resources for learning the new language, such as textbooks or online courses?",
//                                            "How can I practice speaking with native speakers of the new language?",
//                                            "What are some tips for staying motivated to continue learning the new language?",
//                                            "What are some cultural differences that may affect my language learning, and how can I adapt to them?",
//                                            "How can I use music, movies, or other media to improve my language skills?",
//                                            "What are some effective ways to practice writing in the new language?",
//                                            "How can I learn new grammar concepts more easily?",
//                                            "What are some common idioms or expressions in the new language that I should be familiar with?",
//                                            "What are some ways to incorporate the new language into my daily life and routines?",
//                                            "How can I measure my progress in the new language, and set achievable language learning goals?",
//                                        ),
//                                        "English" to listOf(
//                                            "What are some effective ways to improve my English pronunciation?",
//                                            "What are some common English idioms or expressions that I should know?",
//                                            "How can I improve my English vocabulary?",
//                                            "What are some common mistakes English language learners make, and how can I avoid them?",
//                                            "How can I practice speaking English with native speakers?",
//                                            "What are some good resources for learning English, such as textbooks or online courses?",
//                                            "What are some ways to improve my English listening comprehension?",
//                                            "How can I practice my English writing skills?",
//                                            "What are some effective ways to memorize new English words?",
//                                            "How can I use technology to help me learn English?",
//                                            "What are some common English grammar rules that I need to know?",
//                                            "How can I incorporate English into my daily life and routines?",
//                                            "What are some tips for building my English reading skills?",
//                                            "How can I measure my progress in English and set achievable language learning goals?",
//                                            "What are some cultural differences that may affect my English learning, and how can I adapt to them?",
//                                            "How can I practice my English speaking skills on my own?",
//                                            "What are some effective strategies for learning English grammar?",
//                                            "What are some ways to stay motivated when learning English?",
//                                            "How can I use music, movies, or other media to improve my English skills?",
//                                            "What are some tips for building confidence when speaking English?",),
//                                        "Japanese" to listOf(
//                                            "What are some effective ways to improve my Japanese pronunciation?",
//                                            "What are some common Japanese expressions that I should know?",
//                                            "How can I improve my Japanese vocabulary?",
//                                            "What are some common mistakes Japanese language learners make, and how can I avoid them?",
//                                            "How can I practice speaking Japanese with native speakers?",
//                                            "What are some good resources for learning Japanese, such as textbooks or online courses?",
//                                            "What are some ways to improve my Japanese listening comprehension?",
//                                            "How can I practice my Japanese writing skills?",
//                                            "What are some effective ways to memorize new Japanese words?",
//                                            "How can I use technology to help me learn Japanese?",
//                                            "What are some common Japanese grammar rules that I need to know?",
//                                            "How can I incorporate Japanese into my daily life and routines?",
//                                            "What are some tips for building my Japanese reading skills?",
//                                            "How can I measure my progress in Japanese and set achievable language learning goals?",
//                                            "What are some cultural differences that may affect my Japanese learning, and how can I adapt to them?",
//                                            "How can I practice my Japanese speaking skills on my own?",
//                                            "What are some effective strategies for learning Japanese grammar?",
//                                            "What are some ways to stay motivated when learning Japanese?",
//                                            "How can I use music, movies, or other media to improve my Japanese skills?",
//                                            "What are some tips for building confidence when speaking Japanese?",
//                                        ),
//                                        "Korean" to listOf(
//                                            "What are some effective ways to improve my Korean pronunciation?",
//                                            "What are some common Korean expressions that I should know?",
//                                            "How can I improve my Korean vocabulary?",
//                                            "What are some common mistakes Korean language learners make, and how can I avoid them?",
//                                            "How can I practice speaking Korean with native speakers?",
//                                            "What are some good resources for learning Korean, such as textbooks or online courses?",
//                                            "What are some ways to improve my Korean listening comprehension?",
//                                            "How can I practice my Korean writing skills?",
//                                            "What are some effective ways to memorize new Korean words?",
//                                            "How can I use technology to help me learn Korean?",
//                                            "What are some common Korean grammar rules that I need to know?",
//                                            "How can I incorporate Korean into my daily life and routines?",
//                                            "What are some tips for building my Korean reading skills?",
//                                            "How can I measure my progress in Korean and set achievable language learning goals?",
//                                            "What are some cultural differences that may affect my Korean learning, and how can I adapt to them?",
//                                            "How can I practice my Korean speaking skills on my own?",
//                                            "What are some effective strategies for learning Korean grammar?",
//                                            "What are some ways to stay motivated when learning Korean?",
//                                            "How can I use music, movies, or other media to improve my Korean skills?",
//                                            "What are some tips for building confidence when speaking Korean?",
//                                        ),
//                                        "Programming" to listOf(
//                                            "What are some essential programming concepts that every beginner should know?",
//                                            "What are some popular programming languages and their uses?",
//                                            "How can I improve my programming skills and become a better programmer?",
//                                            "What are some good resources for learning programming, such as online courses or books?",
//                                            "What are some common mistakes that beginner programmers make, and how can I avoid them?",
//                                            "How can I find and fix errors in my code?",
//                                            "What are some tools or software that can help me with programming?",
//                                            "How can I write efficient and effective code?",
//                                            "What are some best practices for coding and developing software?",
//                                            "What are some common programming frameworks and libraries that I should know about?",
//                                            "What are some strategies for debugging and testing my code?",
//                                            "How can I stay up-to-date with the latest programming trends and technologies?",
//                                            "What are some programming languages that are good for web development?",
//                                            "What are some popular programming languages for mobile app development?",
//                                            "What are some good resources for learning how to build websites?",
//                                            "How can I learn to build games or other interactive software?",
//                                            "What are some tips for working collaboratively with other programmers?",
//                                            "What are some best practices for code documentation and commenting?",
//                                            "How can I build a portfolio of projects to showcase my programming skills?",
//                                            "What are some potential career paths or job opportunities for programmers?",
//                                        ),
//                                        "Computer Science Fundamentals" to listOf(
//                                            "What are some of the key concepts in computer science?",
//                                            "What is the role of algorithms in computer science?",
//                                            "What are the different data structures commonly used in computer science?",
//                                            "How does computer memory work, and what are the implications for programming?",
//                                            "What is the difference between low-level and high-level programming languages?",
//                                            "How do computer networks work, and what are some common networking protocols?",
//                                            "What is computer security, and how can I learn to write more secure code?",
//                                            "How can I optimize the performance of my code?",
//                                            "What are some common software design patterns, and when should I use them?",
//                                            "What are some best practices for software testing?",
//                                            "What is the difference between imperative and functional programming?",
//                                            "What is the role of machine learning in computer science?",
//                                            "What are some common computer science problems, and how can I solve them?",
//                                            "How can I stay up-to-date with the latest computer science research and trends?",
//                                            "What are some good resources for learning computer science fundamentals, such as online courses or books?",
//                                            "How can I build a strong foundation in computer science?",
//                                            "What are some common misconceptions about computer science, and how can I avoid them?",
//                                            "How can I become a better problem solver and critical thinker in computer science?",
//                                            "What are some potential career paths or job opportunities in computer science?",
//                                            "How can I apply computer science fundamentals to real-world problems and projects?",
//                                        ),
//                                        "Security" to listOf(
//                                            "What are some best practices for securing my code?",
//                                            "How can I prevent SQL injection attacks in my code?",
//                                            "What are some common vulnerabilities in web applications, and how can I mitigate them?",
//                                            "What is cross-site scripting (XSS), and how can I prevent it?",
//                                            "How can I secure user authentication and authorization in my code?",
//                                            "What is the difference between encryption and hashing, and how can I use them to secure my data?",
//                                            "What are some common security pitfalls in JavaScript, and how can I avoid them?",
//                                            "What are some good resources for learning about secure coding practices?",
//                                            "How can I prevent man-in-the-middle attacks when transmitting sensitive data?",
//                                            "What are some common network security protocols, and how can I use them to secure my code?",
//                                            "What is the role of secure sockets layer (SSL) in web security?",
//                                            "How can I secure my server infrastructure, such as firewalls and intrusion detection systems?",
//                                            "What are some common authentication mechanisms, and how can I choose the right one for my project?",
//                                            "How can I perform security audits on my code and infrastructure to identify potential vulnerabilities?",
//                                            "How can I use encryption to protect sensitive data at rest, such as in a database?",
//                                            "What are some common social engineering tactics, and how can I prevent them from compromising my code or infrastructure?",
//                                            "What is multi-factor authentication, and how can I implement it in my code?",
//                                            "What is a virtual private network (VPN), and how can it be used to secure network communications?",
//                                            "What are some best practices for securely storing and handling user passwords?",
//                                            "How can I stay up-to-date with the latest security threats and best practices in the industry?",
//                                        ),
//                                        "SAT" to listOf(
//                                            "What is the SAT, and how is it structured?",
//                                            "What are some general tips for preparing for the SAT?",
//                                            "How can I improve my reading comprehension skills for the SAT?",
//                                            "What is the format of the SAT writing and language section?",
//                                            "What are some common grammar and punctuation rules tested on the SAT writing and language section?",
//                                            "How can I improve my vocabulary for the SAT?",
//                                            "What types of math problems are typically tested on the SAT?",
//                                            "What are some tips for improving my speed and accuracy on the SAT math section?",
//                                            "How can I use process of elimination to help me answer SAT questions?",
//                                            "What is the essay section of the SAT, and how is it scored?",
//                                            "What are some strategies for analyzing and responding to SAT essay prompts?",
//                                            "How can I manage my time effectively during the SAT exam?",
//                                            "What is the best way to approach multiple-choice questions on the SAT?",
//                                            "How can I use context clues to help me answer SAT reading comprehension questions?",
//                                            "What are some common SAT math formulas and concepts that I should know?",
//                                            "What is the scoring system for the SAT, and how are scores calculated?",
//                                            "How can I evaluate my performance on practice SAT tests to identify areas for improvement?",
//                                            "What is the difference between the SAT and the ACT, and which test should I take?",
//                                            "What are some good resources for practicing for the SAT?",
//                                            "What should I do in the days leading up to the SAT to prepare myself mentally and physically?",
//                                        ),
//                                    ),
//                                    "summary" to mapOf(
//                                        //-----23년 02월 23일 여기까지 처리함-----
//                                        "Dissertation" to listOf("dissertation sentence"),
//                                        "Blog" to listOf("Blog sentence")
//                                    ),
//                                    "automation" to mapOf(
//                                        "Excel" to listOf("excel sentence"),
//                                        "ppt" to listOf("ppt sentence"),
//                                        "Marketing" to listOf("marketing sentence"),
//                                        "Copyright automation" to listOf("copyright automation sentence"),
//                                        "Video production" to listOf("video production sentence")
//                                    ),
//                                    "painting" to mapOf(
//                                        "Thumbnail" to listOf("thumbnail sentence")
//                                    ),
//                                    "finance" to mapOf(
//                                        "Portfolio" to listOf("Portfolio sentence"),
//                                        "Company Analysis" to listOf("Company Analysis sentence")),
//                                ),
//                        "detailkorea" to
//                                mapOf(
//                                    "여행 계획" to mapOf(
//                                        "한국" to listOf("한국 문장"),
//                                        "동남아" to listOf("동남아 문장"),
//                                        "미국" to listOf("미국 문장"),
//                                        "유럽" to listOf("유럽 문장"),
//                                        "멕시코" to listOf("멕시코 문장")),
//                                    "학습" to mapOf(
//                                        "교육 기획" to listOf("기획자문장"),
//                                        "외국어" to listOf("외국어 문장"),
//                                        "영어" to kr,
//                                        "일본어" to listOf("일본어 문장"),
//                                        "한국어" to listOf("한국어 문장"),
//                                        "프로그래밍" to listOf("프로그래밍 문장"),
//                                        "Computer Science 기초" to listOf("Computer Science 기초 문장"),
//                                        "보안" to listOf("보안 문장"),
//                                        "수능" to listOf("수능 문장"),
//                                    ),
//                                    "요약" to mapOf(
//                                        "논문" to listOf("논문 문장"),
//                                        "블로그" to listOf("블로그 문장"),
//                                    ),
//                                    "업무자동화" to mapOf(
//                                        "excel" to listOf("excel 문장"),
//                                        "ppt" to listOf("ppt 문장"),
//                                        "마케팅" to listOf("마케팅 문장"),
//                                        "카피라잇 자동화" to listOf("카피라잇 자동화 문장"),
//                                        "영상 제작" to listOf("영상 제작 문장"),
//                                    ),
//                                    "그림" to mapOf(
//                                        "썸네일" to listOf("썸네일 문장"),
//                                    ),
//                                    "금융" to mapOf(
//                                        "포트폴리오" to listOf("포트폴리오 문장"),
//                                        "기업 분석" to listOf("기업 분석 문장")
//                                    ),
//                                ),
//                        "detailjapan" to
//                                mapOf(
//                                    //여행 계획
//                                    "りょこうプラン" to mapOf(
//                                        "かんこく" to listOf("かんこく もんしょう"),
//                                        "とうなん" to listOf("とうなん もんしょう"),
//                                        "アメリカ" to listOf("アメリカ もんしょう"),
//                                        "ヨーロッパ" to listOf("ヨーロッパ もんしょう"),
//                                        "メキシコ" to listOf("メキシコ もんしょう")),
//                                    //학습
//                                    "がくしゅう" to mapOf(
//                                        "きょういく企画きかく" to listOf("きかくしゃ もんしょう"),
//                                        "がいご" to listOf("がいご もんしょう"),
//                                        "えいご" to jp,
//                                        "にほんご" to listOf("にほんご もんしょう"),
//                                        "かんこくご" to listOf("かんこくご もんしょう"),
//                                        "プログラミング" to listOf("プログラミング もんしょう"),
//                                        "コンピューター かがく きそ" to listOf("コンピューター かがく きそ もんしょう"),
//                                        "セキュリティー" to listOf("セキュリティー もんしょう"),
//                                        "大学入試センター試験" to listOf("大学入試センター試験 もんしょう"),
//                                    ),
//                                    //요약
//                                    "ようやく" to mapOf(
//                                        "ろんぶん" to listOf("ろんぶん もんしょう"),
//                                        "ブログ" to listOf("ブログ もんしょう"),
//                                    ),
//                                    //업무자동화
//                                    "ぎょうむ じどうか" to mapOf(
//                                        "エクセル" to listOf("エクセル もんしょう"),
//                                        "ppt" to listOf("ppt もんしょう"),
//                                        "マーケッティング, マーケ" to listOf("マーケッティング, マーケ もんしょう"),
//                                        "コピーライト じどうか" to listOf("コピーライト じどうか もんしょう"),
//                                        "えいぞう せいさく" to listOf("えいぞう せいさく もんしょう"),
//                                    ),
//                                    //그림
//                                    "え" to mapOf(
//                                        "サムネイル" to listOf("サムネイル もんしょう"),
//                                    ),
//                                    //금융
//                                    "きんゆう" to mapOf(
//                                        "ポートフォリオ" to listOf("ポートフォリオ もんしょう"),
//                                        "きぎょう ぶんせき" to listOf("きぎょう ぶんせき もんしょう")
//                                    ),
//                                ),
//                        "detailgerman" to
//                                mapOf(
//                                    "Reiseplan" to mapOf(
//                                        "Korea" to listOf("한국 문장"),
//                                        "Südostasien" to listOf("동남아 문장"),
//                                        "USA" to listOf("미국 문장"),
//                                        "Europa" to listOf("유럽 문장"),
//                                        "Mexiko" to listOf("멕시코 문장")),
//                                    "Lernen" to mapOf(
//                                        "Bildungsplanung" to listOf("기획자문장"),
//                                        "Fremdsprache" to listOf("외국어 문장"),
//                                        "Englisch" to kr,
//                                        "japanisch" to listOf("일본어 문장"),
//                                        "Koreanisch" to listOf("한국어 문장"),
//                                        "Programmierung" to listOf("프로그래밍 문장"),
//                                        "Grundlagen der Informatik" to listOf("Computer Science 기초 문장"),
//                                        "Sicherheit" to listOf("보안 문장"),
//                                        "Abitur" to listOf("수능 문장"),
//                                    ),
//                                    "Zusammenfassung" to mapOf(
//                                        "These" to listOf("논문 문장"),
//                                        "bloggen" to listOf("블로그 문장"),
//                                    ),
//                                    "Automatisierung" to mapOf(
//                                        "übertreffen" to listOf("excel 문장"),
//                                        "ppt" to listOf("ppt 문장"),
//                                        "Marketing" to listOf("마케팅 문장"),
//                                        "Automatisierung des Verfassens von Texten" to listOf("카피라잇 자동화 문장"),
//                                        "Video Produktion" to listOf("영상 제작 문장"),
//                                    ),
//                                    "malen" to mapOf(
//                                        "Miniaturansicht" to listOf("썸네일 문장"),
//                                    ),
//                                    "Finanzen" to mapOf(
//                                        "Portfolio" to listOf("포트폴리오 문장"),
//                                        "Unternehmensanalyse" to listOf("기업 분석 문장")
//                                    ),
//                                )
//                    )
//                )
//
//            db.collection("detailcategory")
//                .document("detailcategorylist")
//                .update(test)



            //"fashion" to listOf("excel","ppt","마케팅","카피라잇 자동화", "영상 제작")

            //데이터 셋업용
            val testSetUp =
                mapOf(
                    "categorylist" to mapOf(
                        "detailenglish" to
                                mapOf(
                                    "travel plan" to mapOf(
                                        "Korea" to listOf(
                                            "When is the best time of year to visit Korea?"
                                        ),
                                        "Southeast Asia" to listOf(
                                            "When is the best time of year to visit Southeast Asia?"
                                        ),
                                        "America" to listOf(
                                            "What are some popular tourist attractions in the US that are a must-visit?",
                                        ),
                                        "Europe" to listOf(
                                            "Are there any unique experiences or activities in Europe that I shouldn't miss?",
                                        ),
                                        "Mexico" to listOf(
                                            "What are some popular tourist attractions in Mexico that are a must-visit?",
                                        ),
                                    ),
                                    "learning" to mapOf(
                                        "Education Planning" to listOf(
                                            "What are my options for further education, and what are the admission requirements?",
                                        ),
                                        "Foreign Language" to listOf(
                                            "What are some effective strategies for learning a new language?",
                                        ),
                                        "English" to listOf(
                                            "What are some effective ways to improve my English pronunciation?",
                                        ),
                                        "Japanese" to listOf(
                                            "What are some effective ways to improve my Japanese pronunciation?",
                                        ),
                                        "Korean" to listOf(
                                            "What are some effective ways to improve my Korean pronunciation?",
                                        ),
                                        "Programming" to listOf(
                                            "What are some essential programming concepts that every beginner should know?",
                                        ),
                                        "Computer Science Fundamentals" to listOf(
                                            "What are some of the key concepts in computer science?",
                                        ),
                                        "Security" to listOf(
                                            "What are some best practices for securing my code?",
                                        ),
                                        "SAT" to listOf(
                                            "What is the SAT, and how is it structured?",
                                        ),
                                    ),
                                    "summary" to mapOf(
                                        //-----23년 02월 23일 여기까지 처리함-----
                                        "Dissertation" to listOf(
                                            "dissertation sentence",
                                        ),
                                        "Blog" to listOf(
                                            "Blog sentence",
                                        )
                                    ),
                                    "automation" to mapOf(
                                        "Excel" to listOf("excel sentence"),
                                        "ppt" to listOf("ppt sentence"),
                                        "Marketing" to listOf("marketing sentence"),
                                        "Copyright automation" to listOf("copyright automation sentence"),
                                        "Video production" to listOf("video production sentence")
                                    ),
                                    "painting" to mapOf(
                                        "Thumbnail" to listOf("thumbnail sentence")
                                    ),
                                    "finance" to mapOf(
                                        "Portfolio" to listOf("Portfolio sentence"),
                                        "Company Analysis" to listOf("Company Analysis sentence")),
                                ),
                        "detailkorea" to
                                mapOf(
                                    "여행 계획" to mapOf(
                                        "한국" to listOf("한국 문장"),
                                        "동남아" to listOf("동남아 문장"),
                                        "미국" to listOf("미국 문장"),
                                        "유럽" to listOf("유럽 문장"),
                                        "멕시코" to listOf("멕시코 문장")),
                                    "학습" to mapOf(
                                        "교육 기획" to listOf("기획자문장"),
                                        "외국어" to listOf("외국어 문장"),
                                        "영어" to kr,
                                        "일본어" to listOf("일본어 문장"),
                                        "한국어" to listOf("한국어 문장"),
                                        "프로그래밍" to listOf("프로그래밍 문장"),
                                        "Computer Science 기초" to listOf("Computer Science 기초 문장"),
                                        "보안" to listOf("보안 문장"),
                                        "수능" to listOf("수능 문장"),
                                    ),
                                    "요약" to mapOf(
                                        "논문" to listOf("논문 문장"),
                                        "블로그" to listOf("블로그 문장"),
                                    ),
                                    "업무자동화" to mapOf(
                                        "excel" to listOf("excel 문장"),
                                        "ppt" to listOf("ppt 문장"),
                                        "마케팅" to listOf("마케팅 문장"),
                                        "카피라잇 자동화" to listOf("카피라잇 자동화 문장"),
                                        "영상 제작" to listOf("영상 제작 문장"),
                                    ),
                                    "그림" to mapOf(
                                        "썸네일" to listOf("썸네일 문장"),
                                    ),
                                    "금융" to mapOf(
                                        "포트폴리오" to listOf("포트폴리오 문장"),
                                        "기업 분석" to listOf("기업 분석 문장")
                                    ),
                                ),
                        "detailjapan" to
                                mapOf(
                                    //여행 계획
                                    "りょこうプラン" to mapOf(
                                        "かんこく" to listOf("かんこく もんしょう"),
                                        "とうなん" to listOf("とうなん もんしょう"),
                                        "アメリカ" to listOf("アメリカ もんしょう"),
                                        "ヨーロッパ" to listOf("ヨーロッパ もんしょう"),
                                        "メキシコ" to listOf("メキシコ もんしょう")),
                                    //학습
                                    "がくしゅう" to mapOf(
                                        "きょういく企画きかく" to listOf("きかくしゃ もんしょう"),
                                        "がいご" to listOf("がいご もんしょう"),
                                        "えいご" to jp,
                                        "にほんご" to listOf("にほんご もんしょう"),
                                        "かんこくご" to listOf("かんこくご もんしょう"),
                                        "プログラミング" to listOf("プログラミング もんしょう"),
                                        "コンピューター かがく きそ" to listOf("コンピューター かがく きそ もんしょう"),
                                        "セキュリティー" to listOf("セキュリティー もんしょう"),
                                        "大学入試センター試験" to listOf("大学入試センター試験 もんしょう"),
                                    ),
                                    //요약
                                    "ようやく" to mapOf(
                                        "ろんぶん" to listOf("ろんぶん もんしょう"),
                                        "ブログ" to listOf("ブログ もんしょう"),
                                    ),
                                    //업무자동화
                                    "ぎょうむ じどうか" to mapOf(
                                        "エクセル" to listOf("エクセル もんしょう"),
                                        "ppt" to listOf("ppt もんしょう"),
                                        "マーケッティング, マーケ" to listOf("マーケッティング, マーケ もんしょう"),
                                        "コピーライト じどうか" to listOf("コピーライト じどうか もんしょう"),
                                        "えいぞう せいさく" to listOf("えいぞう せいさく もんしょう"),
                                    ),
                                    //그림
                                    "え" to mapOf(
                                        "サムネイル" to listOf("サムネイル もんしょう"),
                                    ),
                                    //금융
                                    "きんゆう" to mapOf(
                                        "ポートフォリオ" to listOf("ポートフォリオ もんしょう"),
                                        "きぎょう ぶんせき" to listOf("きぎょう ぶんせき もんしょう")
                                    ),
                                ),
                        "detailgerman" to
                                mapOf(
                                    "Reiseplan" to mapOf(
                                        "Korea" to listOf("한국 문장"),
                                        "Südostasien" to listOf("동남아 문장"),
                                        "USA" to listOf("미국 문장"),
                                        "Europa" to listOf("유럽 문장"),
                                        "Mexiko" to listOf("멕시코 문장")),
                                    "Lernen" to mapOf(
                                        "Bildungsplanung" to listOf("기획자문장"),
                                        "Fremdsprache" to listOf("외국어 문장"),
                                        "Englisch" to kr,
                                        "japanisch" to listOf("일본어 문장"),
                                        "Koreanisch" to listOf("한국어 문장"),
                                        "Programmierung" to listOf("프로그래밍 문장"),
                                        "Grundlagen der Informatik" to listOf("Computer Science 기초 문장"),
                                        "Sicherheit" to listOf("보안 문장"),
                                        "Abitur" to listOf("수능 문장"),
                                    ),
                                    "Zusammenfassung" to mapOf(
                                        "These" to listOf("논문 문장"),
                                        "bloggen" to listOf("블로그 문장"),
                                    ),
                                    "Automatisierung" to mapOf(
                                        "übertreffen" to listOf("excel 문장"),
                                        "ppt" to listOf("ppt 문장"),
                                        "Marketing" to listOf("마케팅 문장"),
                                        "Automatisierung des Verfassens von Texten" to listOf("카피라잇 자동화 문장"),
                                        "Video Produktion" to listOf("영상 제작 문장"),
                                    ),
                                    "malen" to mapOf(
                                        "Miniaturansicht" to listOf("썸네일 문장"),
                                    ),
                                    "Finanzen" to mapOf(
                                        "Portfolio" to listOf("포트폴리오 문장"),
                                        "Unternehmensanalyse" to listOf("기업 분석 문장")
                                    ),
                                )
                    )
                )


            val test22 =
                mapOf(
                    "categorylist" to mapOf(
                        "detailgerman" to
                                mapOf(
                                    "Automatisierung" to mapOf(
                                        "Video Produktion" to kr
                                    ),
                                )
                    )
            )

            db.collection("detailcategory")
                .document("detailcategorylist")
                .set(test22, SetOptions.merge())


        }


    }


}