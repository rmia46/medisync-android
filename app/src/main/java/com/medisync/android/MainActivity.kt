package com.medisync.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.medisync.android.core.theme.MediSyncTheme
import com.medisync.android.presentation.alerts.AlertsViewModel
import com.medisync.android.presentation.alternatives.AlternativesViewModel
import com.medisync.android.presentation.auth.AuthViewModel
import com.medisync.android.presentation.auth.AuthViewModelFactory
import com.medisync.android.presentation.dispenser.DispenserViewModel
import com.medisync.android.presentation.doctor.DoctorViewModel
import com.medisync.android.presentation.navigation.MediSyncNavGraph
import com.medisync.android.presentation.pharmacy.PharmacyViewModel
import com.medisync.android.presentation.prescription.PrescriptionViewModel
import com.medisync.android.presentation.totp.TotpViewModel
import com.medisync.android.presentation.triage.TriageViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val app = application as MediSyncApplication

        setContent {
            MediSyncTheme {
                val navController = rememberNavController()
                val authViewModel: AuthViewModel = viewModel(
                    factory = AuthViewModelFactory(app.authRepository)
                )
                val triageViewModel: TriageViewModel = viewModel(
                    factory = object : ViewModelProvider.Factory {
                        @Suppress("UNCHECKED_CAST")
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return TriageViewModel(app.triageRepository) as T
                        }
                    }
                )
                val prescriptionViewModel: PrescriptionViewModel = viewModel(
                    factory = object : ViewModelProvider.Factory {
                        @Suppress("UNCHECKED_CAST")
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return PrescriptionViewModel(app.prescriptionRepository) as T
                        }
                    }
                )
                val alternativesViewModel: AlternativesViewModel = viewModel(
                    factory = object : ViewModelProvider.Factory {
                        @Suppress("UNCHECKED_CAST")
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return AlternativesViewModel(app.alternativesRepository) as T
                        }
                    }
                )
                val pharmacyViewModel: PharmacyViewModel = viewModel(
                    factory = object : ViewModelProvider.Factory {
                        @Suppress("UNCHECKED_CAST")
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return PharmacyViewModel(app.pharmacyRepository) as T
                        }
                    }
                )
                val alertsViewModel: AlertsViewModel = viewModel(
                    factory = object : ViewModelProvider.Factory {
                        @Suppress("UNCHECKED_CAST")
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return AlertsViewModel(app.alertsRepository, app.alarmScheduler) as T
                        }
                    }
                )
                val totpViewModel: TotpViewModel = viewModel(
                    factory = object : ViewModelProvider.Factory {
                        @Suppress("UNCHECKED_CAST")
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return TotpViewModel(app.totpRepository) as T
                        }
                    }
                )
                val doctorViewModel: DoctorViewModel = viewModel(
                    factory = object : ViewModelProvider.Factory {
                        @Suppress("UNCHECKED_CAST")
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return DoctorViewModel(app.ehrRepository) as T
                        }
                    }
                )
                val dispenserViewModel: DispenserViewModel = viewModel(
                    factory = object : ViewModelProvider.Factory {
                        @Suppress("UNCHECKED_CAST")
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return DispenserViewModel(app.dispenserRepository) as T
                        }
                    }
                )

                MediSyncNavGraph(
                    navController = navController,
                    authViewModel = authViewModel,
                    triageViewModel = triageViewModel,
                    prescriptionViewModel = prescriptionViewModel,
                    alternativesViewModel = alternativesViewModel,
                    pharmacyViewModel = pharmacyViewModel,
                    alertsViewModel = alertsViewModel,
                    totpViewModel = totpViewModel,
                    doctorViewModel = doctorViewModel,
                    dispenserViewModel = dispenserViewModel
                )
            }
        }
    }
}
