package com.example.saludmental.data

import com.example.saludmental.data.models.Appointment
import com.example.saludmental.data.models.MusicTrack
import com.example.saludmental.data.models.PsychologyPost
import java.util.Calendar

object MockData {

    val psychologyPosts = listOf(
        PsychologyPost(
            id = "1",
            title = "El poder de la respiración consciente",
            content = "La respiración consciente es una técnica simple pero poderosa que puede reducir el estrés y la ansiedad en minutos. Cuando respiramos de manera consciente, activamos el sistema nervioso parasimpático, promoviendo la relajación.",
            category = "Técnicas de Relajación"
        ),
        PsychologyPost(
            id = "2",
            title = "¿Sabías que el ejercicio mejora tu estado de ánimo?",
            content = "El ejercicio físico libera endorfinas, conocidas como las 'hormonas de la felicidad'. Solo 20 minutos de actividad física pueden mejorar significativamente tu estado de ánimo y reducir síntomas de depresión.",
            category = "Bienestar"
        ),
        PsychologyPost(
            id = "3",
            title = "La importancia del sueño en la salud mental",
            content = "Dormir bien es fundamental para el bienestar psicológico. Durante el sueño, nuestro cerebro procesa emociones y consolida memorias. La falta de sueño puede aumentar el riesgo de ansiedad y depresión.",
            category = "Hábitos Saludables"
        ),
        PsychologyPost(
            id = "4",
            title = "Técnica 5-4-3-2-1 para la ansiedad",
            content = "Esta técnica de grounding te ayuda a enfocarte en el presente: identifica 5 cosas que puedes ver, 4 que puedes tocar, 3 que puedes escuchar, 2 que puedes oler y 1 que puedes saborear.",
            category = "Manejo de Ansiedad"
        )
    )

    val musicTracks = listOf(
        MusicTrack(
            id = "1",
            title = "Weightless",
            artist = "Marconi Union",
            benefits = "Científicamente diseñada para reducir la ansiedad en un 65%. Perfecta para meditation y relajación profunda.",
            spotifyUrl = "https://open.spotify.com/track/example1",
            youtubeUrl = "https://youtube.com/watch?v=example1",
            genre = "Ambient"
        ),
        MusicTrack(
            id = "2",
            title = "Aqueous Transmission",
            artist = "Incubus",
            benefits = "Sus sonidos suaves y progresivos ayudan a calmar la mente y reducir el estrés después de un día intenso.",
            spotifyUrl = "https://open.spotify.com/track/example2",
            youtubeUrl = "https://youtube.com/watch?v=example2",
            genre = "Alternative"
        ),
        MusicTrack(
            id = "3",
            title = "Music for Airports",
            artist = "Brian Eno",
            benefits = "Música ambiental pionera que crea un espacio mental tranquilo, ideal para concentración y paz interior.",
            spotifyUrl = "https://open.spotify.com/track/example3",
            youtubeUrl = "https://youtube.com/watch?v=example3",
            genre = "Ambient"
        ),
        MusicTrack(
            id = "4",
            title = "River",
            artist = "Max Richter",
            benefits = "Composición minimalista que promueve la introspección y ayuda a procesar emociones de manera saludable.",
            spotifyUrl = "https://open.spotify.com/track/example4",
            youtubeUrl = "https://youtube.com/watch?v=example4",
            genre = "Neoclassical"
        )
    )

    val appointments = listOf(
        Appointment(
            id = "1",
            title = "Consulta inicial - Evaluación",
            psychologistName = "Dra. María González",
            date = Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, 2) }.time,
            time = "10:00 AM",
            location = "Consultorio 201, Centro de Bienestar",
            isVirtual = false
        ),
        Appointment(
            id = "2",
            title = "Terapia cognitivo-conductual",
            psychologistName = "Dr. Carlos Ruiz",
            date = Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, 5) }.time,
            time = "3:00 PM",
            location = "Sesión Virtual",
            isVirtual = true,
            virtualLink = "https://meet.google.com/abc-defg-hij"
        ),
        Appointment(
            id = "3",
            title = "Seguimiento - Manejo de estrés",
            psychologistName = "Lic. Ana Martínez",
            date = Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, 8) }.time,
            time = "11:30 AM",
            location = "Consultorio 105, Edificio A",
            isVirtual = false
        )
    )
}