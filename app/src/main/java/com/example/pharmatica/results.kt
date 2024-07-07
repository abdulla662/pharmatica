package com.example.pharmatica

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore


class results : AppCompatActivity() {
    private lateinit var medicaltext:EditText
    lateinit var textView:TextView
    private lateinit var imageView5:ImageView
 val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        medicaltext=findViewById(R.id.medicaltext)
        textView=findViewById(R.id.textView)
        imageView5=findViewById(R.id.imageView5)
        upload()
        imageView5.setOnClickListener {
            search()
        }

    }


    fun call(view: View) {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:123")
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("Permission", "CALL_PHONE permission not granted")
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
            return
        }
        Log.d("Calling", "Starting call intent")
        startActivity(intent)
    }
    fun upload(){
            val db = FirebaseFirestore.getInstance()

            val medicinesData = mapOf(
                "panadol" to mapOf(
                    "active_substance" to "Paracetamol",
                    "uses" to "Pain relief, fever reduction",
                    "side_effects" to "Rare liver damage with overdose",
                    "contraindications" to "Hypersensitivity to paracetamol",
                    "dosage" to "500 mg to 1 g every 4-6 hours (max 4 g/day)",
                    "forms" to listOf("Tablets (500 mg)", "Liquid Suspension (120 mg/5 ml)", "Suppositories"),
                    "price" to listOf("10 tablets (5 EGP)", "100 ml (8 EGP)")
                ),
                "brufen" to mapOf(
                    "active_substance" to "Ibuprofen",
                    "uses" to "Pain relief, anti-inflammatory, fever reduction",
                    "side_effects" to "Gastrointestinal upset, ulceration, kidney issues",
                    "contraindications" to "History of gastrointestinal bleeding, severe kidney disease",
                    "dosage" to "200-400 mg every 4-6 hours (max 1.2 g/day)",
                    "forms" to listOf("Tablets (200 mg, 400 mg)", "Liquid Suspension (100 mg/5 ml)", "Gel"),
                    "price" to listOf("20 tablets (10 EGP)", "100 ml (12 EGP)")
                ),
                "augmentin" to mapOf(
                    "active_substance" to "Amoxicillin/Clavulanic Acid",
                    "uses" to "Bacterial infections",
                    "side_effects" to "Allergic reactions, gastrointestinal upset",
                    "contraindications" to "Hypersensitivity to penicillins, history of liver problems",
                    "dosage" to "Varies based on infection severity",
                    "forms" to listOf("Tablets (500 mg, 875 mg)", "Liquid Suspension (125 mg/5 ml)", "IV"),
                    "price" to listOf("10 tablets (20 EGP)", "100 ml (25 EGP)")
                ),
                "cataflam" to mapOf(
                    "active_substance" to "Diclofenac Potassium",
                    "uses" to "Pain relief, anti-inflammatory",
                    "side_effects" to "Gastrointestinal upset, ulceration, kidney issues",
                    "contraindications" to "History of gastrointestinal bleeding, kidney disease",
                    "dosage" to "25-50 mg three times daily",
                    "forms" to listOf("Tablets (25 mg, 50 mg)", "Gel"),
                    "price" to listOf("20 tablets (15 EGP)", "50 g (20 EGP)")
                ),
                "omeprazole" to mapOf(
                    "active_substance" to "Losec, Prilosec",
                    "uses" to "GERD, peptic ulcer disease",
                    "side_effects" to "Headache, gastrointestinal upset",
                    "contraindications" to "Hypersensitivity to omeprazole, severe liver disease",
                    "dosage" to "20-40 mg once daily",
                    "forms" to listOf("Capsules (20 mg, 40 mg)", "Tablets"),
                    "price" to listOf("14 capsules (25 EGP)", "20 tablets (30 EGP)")
                ),
                "ventolin" to mapOf(
                    "active_substance" to "Salbutamol",
                    "uses" to "Asthma, COPD",
                    "side_effects" to "Tremors, palpitations, headache",
                    "contraindications" to "Hypersensitivity to salbutamol, severe heart conditions",
                    "dosage" to "Inhaler: 100-200 mcg every 4-6 hours as needed",
                    "forms" to listOf("Inhaler (100 mcg/puff)", "Nebulizer Solution", "Tablets"),
                    "price" to listOf("Inhaler (60 puffs) (30 EGP)", "100 tablets (40 EGP)")
                ),
                "concor" to mapOf(
                    "active_substance" to "Bisoprolol",
                    "uses" to "Hypertension, heart failure",
                    "side_effects" to "Edema, dizziness, palpitations",
                    "contraindications" to "Severe heart conditions, bradycardia",
                    "dosage" to "5-10 mg once daily",
                    "forms" to listOf("Tablets (5 mg, 10 mg)"),
                    "price" to listOf("20 tablets (25 EGP)", "50 tablets (50 EGP)")
                ),
                "glucophage" to mapOf(
                    "active_substance" to "Metformin",
                    "uses" to "Type 2 diabetes mellitus",
                    "side_effects" to "Gastrointestinal upset, lactic acidosis (rare)",
                    "contraindications" to "Severe kidney disease, metabolic acidosis",
                    "dosage" to "500 mg to 2 g per day, divided doses",
                    "forms" to listOf("Tablets (500 mg, 850 mg, 1000 mg)"),
                    "price" to listOf("60 tablets (20 EGP)", "100 tablets (30 EGP)")
                ),
                "lipitor" to mapOf(
                    "active_substance" to "Atorvastatin",
                    "uses" to "High cholesterol, cardiovascular prevention",
                    "side_effects" to "Muscle pain, liver enzyme abnormalities",
                    "contraindications" to "Active liver disease, pregnancy",
                    "dosage" to "10-80 mg once daily",
                    "forms" to listOf("Tablets (10 mg, 20 mg, 40 mg, 80 mg)"),
                    "price" to listOf("30 tablets (40 EGP)", "60 tablets (70 EGP)")
                ),
                "lyrica" to mapOf(
                    "active_substance" to "Pregabalin",
                    "uses" to "Neuropathic pain, epilepsy, anxiety",
                    "side_effects" to "Dizziness, drowsiness, weight gain",
                    "contraindications" to "Hypersensitivity to pregabalin, severe heart failure",
                    "dosage" to "Varies based on condition and individual response",
                    "forms" to listOf("Capsules (25 mg, 50 mg, 75 mg, 100 mg, 150 mg, 200 mg, 300 mg)"),
                    "price" to listOf("14 capsules (70 EGP)", "56 capsules (250 EGP)")
                ),
                "zyrtec" to mapOf(
                    "active_substance" to "Cetirizine",
                    "uses" to "Allergic rhinitis, urticaria",
                    "side_effects" to "Drowsiness, dry mouth",
                    "contraindications" to "Severe renal impairment, hypersensitivity to cetirizine",
                    "dosage" to "10 mg once daily",
                    "forms" to listOf("Tablets (10 mg)", "Oral Solution"),
                    "price" to listOf("20 tablets (15 EGP)", "100 ml (20 EGP)")
                ),
                "voltaren" to mapOf(
                    "active_substance" to "Diclofenac Sodium",
                    "uses" to "Pain relief, anti-inflammatory",
                    "side_effects" to "Gastrointestinal upset, ulceration, kidney issues",
                    "contraindications" to "History of gastrointestinal bleeding, kidney disease",
                    "dosage" to "25-50 mg three times daily",
                    "forms" to listOf("Tablets (25 mg, 50 mg)", "Gel", "Suppositories"),
                    "price" to listOf("20 tablets (10 EGP)", "50 g (15 EGP)")
                ),
                "flagyl" to mapOf(
                    "active_substance" to "Metronidazole",
                    "uses" to "Bacterial and protozoal infections",
                    "side_effects" to "Nausea, metallic taste",
                    "contraindications" to "First trimester of pregnancy, history of blood disorders",
                    "dosage" to "500 mg every 8 hours",
                    "forms" to listOf("Tablets (250 mg, 500 mg)", "IV"),
                    "price" to listOf("14 tablets (20 EGP)", "100 ml IV (25 EGP)")
                ),
                "celebrex" to mapOf(
                    "active_substance" to "Celecoxib",
                    "uses" to "Pain relief, anti-inflammatory",
                    "side_effects" to "GI bleeding, cardiovascular events",
                    "contraindications" to "History of allergic reactions to NSAIDs, severe heart failure",
                    "dosage" to "100-200 mg once or twice daily",
                    "forms" to listOf("Capsules (100 mg, 200 mg)"),
                    "price" to listOf("14 capsules (40 EGP)", "28 capsules (70 EGP)")
                ),
                "ciprobay" to mapOf(
                    "active_substance" to "Ciprofloxacin",
                    "uses" to "Bacterial infections",
                    "side_effects" to "Gastrointestinal upset, tendonitis, photosensitivity",
                    "contraindications" to "Hypersensitivity to ciprofloxacin, history of tendon disorders",
                    "dosage" to "250-750 mg every 12 hours",
                    "forms" to listOf("Tablets (250 mg, 500 mg)", "IV"),
                    "price" to listOf("10 tablets (30 EGP)", "100 ml IV (40 EGP)")
                ),
                "advil" to mapOf(
                    "active_substance" to "Ibuprofen",
                    "uses" to "Pain relief, anti-inflammatory",
                    "side_effects" to "Gastrointestinal upset, kidney issues",
                    "contraindications" to "History of gastrointestinal bleeding, severe kidney disease",
                    "dosage" to "200-400 mg every 4-6 hours",
                    "forms" to listOf("Tablets (200 mg, 400 mg)", "Suspension"),
                    "price" to listOf("20 tablets (10 EGP)", "100 ml (15 EGP)")
                )
            )

            for ((medName, medData) in medicinesData) {
                db.collection("medicines").document(medName)
                    .set(medData)
                    .addOnSuccessListener {
                    }
                    .addOnFailureListener { e ->
                        println("Error adding $medName: ${e.message}")
                    }
            }
        }
    fun search() {
        val medName = medicaltext.text.toString().trim()
        if (medName.isNotEmpty()) {
            getMedicineData(medName)
        } else {
            Toast.makeText(this, "Please enter a medicine name", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getMedicineData(medName: String) {
        val docRef = db.collection("medicines").document(medName)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val data = document.data
                    if (data != null) {
                        val resultText = StringBuilder()
                        for ((key, value) in data) {
                            resultText.append("$key: $value\n")
                        }
                        textView.text = resultText.toString()
                    }
                } else {
                    Toast.makeText(this, "No such document found", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Error", "Error fetching document: ${exception.message}")
            }
    }
}

