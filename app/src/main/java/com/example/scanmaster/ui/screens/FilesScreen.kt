package com.example.scanmaster.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scanmaster.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilesScreen() {
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.background(MaterialTheme.colorScheme.background).statusBarsPadding().padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(R.string.my_scans), style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.ExtraBold)
                    Row {
                        IconButton(onClick = { }) {
                            Icon(Icons.Default.CloudUpload, contentDescription = "Cloud", tint = MaterialTheme.colorScheme.primary)
                        }
                        IconButton(onClick = { }) {
                            Icon(Icons.Default.GridView, contentDescription = "Grid", tint = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text(stringResource(R.string.search_files_placeholder)) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray) },
                    modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                        unfocusedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    item {
                        FilterChip(
                            selected = true,
                            onClick = { },
                            label = { Text(stringResource(R.string.date)) },
                            trailingIcon = { Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, modifier = Modifier.size(16.dp)) },
                            colors = FilterChipDefaults.filterChipColors(selectedContainerColor = MaterialTheme.colorScheme.primary, selectedLabelColor = Color.White),
                            border = null
                        )
                    }
                    item { FilterChip(selected = false, onClick = { }, label = { Text(stringResource(R.string.name)) }) }
                    item { FilterChip(selected = false, onClick = { }, label = { Text(stringResource(R.string.size)) }) }
                    item { FilterChip(selected = false, onClick = { }, label = { Text(stringResource(R.string.type)) }) }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { }, containerColor = MaterialTheme.colorScheme.primary, contentColor = Color.White, shape = CircleShape) {
                Icon(Icons.Default.AddAPhoto, contentDescription = "Scan")
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = 24.dp)) {
            items(allFiles) { file ->
                FileListItem(file)
                Spacer(modifier = Modifier.height(12.dp))
            }
            item { Spacer(modifier = Modifier.height(80.dp)) }
        }
    }
}

@Composable
fun FileListItem(file: FileData) {
    Row(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp)).background(MaterialTheme.colorScheme.surface).padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(48.dp).clip(RoundedCornerShape(8.dp)).background(Color(0xFFFFEBEE)), contentAlignment = Alignment.Center) {
            Icon(Icons.Default.PictureAsPdf, contentDescription = null, tint = Color.Red)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = file.name, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(text = "${file.date} • ${file.size}", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
        }
        Row {
            IconButton(onClick = { }) { Icon(Icons.Default.Edit, contentDescription = "Edit", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp)) }
            IconButton(onClick = { }) { Icon(Icons.Default.Share, contentDescription = "Share", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp)) }
            IconButton(onClick = { }) { Icon(Icons.Default.MoreVert, contentDescription = "More", tint = Color.Gray) }
        }
    }
}

data class FileData(val name: String, val date: String, val size: String)
val allFiles = listOf(
    FileData("Project_Proposal_Final.pdf", "Oct 24, 2023", "2.4 MB"),
    FileData("Medical_Record_JohnDoe.pdf", "Oct 22, 2023", "1.8 MB"),
    FileData("Tax_Returns_2023.pdf", "Oct 18, 2023", "5.1 MB"),
    FileData("Lease_Agreement_Signed.pdf", "Oct 15, 2023", "840 KB"),
    FileData("Gym_Membership_Terms.pdf", "Oct 10, 2023", "1.2 MB"),
    FileData("Recipe_Scans_Collection.pdf", "Oct 05, 2023", "12.5 MB")
)
