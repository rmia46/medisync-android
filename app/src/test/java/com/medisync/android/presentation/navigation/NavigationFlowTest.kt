package com.medisync.android.presentation.navigation

import com.medisync.android.data.model.UserRole
import org.junit.Assert.assertEquals
import org.junit.Test

class NavigationFlowTest {

    @Test
    fun `verify all sealed screen routes match expected hierarchy`() {
        assertEquals("welcome", Screen.Welcome.route)
        assertEquals("login/PATIENT", Screen.Login.createRoute(UserRole.PATIENT))
        assertEquals("login/DOCTOR", Screen.Login.createRoute(UserRole.DOCTOR))
        assertEquals("login/PHARMACY", Screen.Login.createRoute(UserRole.PHARMACY))
        assertEquals("register", Screen.Register.route)
        assertEquals("dashboard", Screen.Dashboard.route)
        assertEquals("triage", Screen.Triage.route)
        assertEquals("upload_prescription", Screen.UploadPrescription.route)
        assertEquals("prescription_analysis", Screen.PrescriptionAnalysis.route)
        assertEquals("prescription_wallet", Screen.PrescriptionWallet.route)
        assertEquals("drug_detail/metformin", Screen.DrugDetail.createRoute("metformin"))
        assertEquals("alternatives/napa-extra", Screen.Alternatives.createRoute("napa-extra"))
        assertEquals("pharmacy_locator?drugId=napa-extra", Screen.PharmacyLocator.createRoute("napa-extra"))
        assertEquals("pharmacy_locator", Screen.PharmacyLocator.createRoute(null))
        assertEquals("medication_reminders", Screen.MedicationReminders.route)
        assertEquals("doctor_patients", Screen.DoctorPatientList.route)
        assertEquals("doctor_ehr_timeline", Screen.DoctorEhrTimeline.route)
        assertEquals("pharmacy_dispenser", Screen.PharmacyDispenser.route)
    }
}
