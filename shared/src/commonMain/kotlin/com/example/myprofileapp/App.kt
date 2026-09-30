package com.example.myprofileapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import myprofileapp.shared.generated.resources.Res
import myprofileapp.shared.generated.resources.foto_profil

@Composable
@Preview
fun App() {
    MaterialTheme {
        // Layout 1: Column (Vertical Layout)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .safeContentPadding() // Safe padding for status bars
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Custom Composable 1: ProfileHeader
            ProfileHeader(
                name = "Hezkiel",
                bio = "Informatics Student | Mobile Developer Enthusiast",
                imageRes = Res.drawable.foto_profil 
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            var showContactInfo by remember { mutableStateOf(false) }
            
            // Required component: Button
            Button(
                onClick = { showContactInfo = !showContactInfo },
                modifier = Modifier.fillMaxWidth(0.7f)
            ) {
                Text(if (showContactInfo) "Hide Contact Info" else "Show Contact Info")
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Bonus (+10%): AnimatedVisibility
            AnimatedVisibility(visible = showContactInfo) {
                // Custom Composable 2: ProfileCard
                ProfileCard {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "Contact Information",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(bottom = 4.dp),
                            color = MaterialTheme.colorScheme.primary
                        )
                        
                        // Custom Composable 3: InfoItem
                        InfoItem(icon = "✉️", text = "hezkiel.12140118@student.itera.ac.id")
                        InfoItem(icon = "📞", text = "089651482735")
                        InfoItem(icon = "📍", text = "Lampung, Bandar Lampung")
                    }
                }
            }
        }
    }
}

// -----------------------------------------------------
// 3 Reusable Composable Functions
// -----------------------------------------------------

@Composable
fun ProfileHeader(name: String, bio: String, imageRes: org.jetbrains.compose.resources.DrawableResource) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Layout 2: Box (Stack Layout)
        Box(
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            // Required component: Image
            Image(
                painter = painterResource(imageRes),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
        
        Spacer(modifier = Modifier.height(20.dp))
        
        // Required component: Text
        Text(
            text = name,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = bio,
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun ProfileCard(content: @Composable () -> Unit) {
    // Required component: Card
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        content()
    }
}

@Composable
fun InfoItem(icon: String, text: String) {
    // Layout 3: Row (Horizontal Layout)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = icon,
            fontSize = 24.sp
        )
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Text(
            text = text,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}