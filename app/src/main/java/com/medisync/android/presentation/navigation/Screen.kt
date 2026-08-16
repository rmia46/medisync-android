package com.medisync.android.presentation.navigation

import com.medisync.android.data.model.UserRole

sealed class Screen(val route: String) {
    data object Welcome : Screen("welcome")
    data object Login : Screen("login/{role}") {
        fun createRoute(role: UserRole) = "login/${role.name}"
    }
    data object Register : Screen("register")
    data object Dashboard : Screen("dashboard")
    data object Triage : Screen("triage")
    data object UploadPrescription : Screen("upload_prescription")
    data object PrescriptionAnalysis : Screen("prescription_analysis")
    data object PrescriptionWallet : Screen("prescription_wallet")
    data object DrugDetail : Screen("drug_detail/{drugId}") {
        fun createRoute(drugId: String) = "drug_detail/$drugId"
    }
    data object Alternatives : Screen("alternatives/{drugId}") {
        fun createRoute(drugId: String) = "alternatives/$drugId"
    }
    data object PharmacyLocator : Screen("pharmacy_locator?drugId={drugId}") {
        fun createRoute(drugId: String? = null) = if (drugId != null) "pharmacy_locator?drugId=$drugId" else "pharmacy_locator"
    }
    data object MedicationReminders : Screen("medication_reminders")
    data object DoctorPatientList : Screen("doctor_patients")
    data object DoctorEhrTimeline : Screen("doctor_ehr_timeline")
    data object PharmacyDispenser : Screen("pharmacy_dispenser")
}
