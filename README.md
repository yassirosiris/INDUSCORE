# INDUSCORE# 🚀 Induscore - Industrial IoT & Predictive Maintenance

**Induscore** est une plateforme complète de monitoring industriel conçue pour la surveillance en temps réel et la maintenance prédictive d'équipements critiques (ex: Tours CNC, Compresseurs). 

![Status](https://img.shields.io/badge/Status-Project_Complete-green)
![Java](https://img.shields.io/badge/Language-Java-orange)
![Framework](https://img.shields.io/badge/Framework-Spring_Boot-brightgreen)

---

## 🌟 Fonctionnalités Majeures
* **Real-time Monitoring** : Visualisation des données de température et de vibration via une interface JavaFX.
* **Analyse Prédictive** : Intégration de **Weka (Machine Learning)** pour anticiper les pannes avant qu'elles ne surviennent.
* **Gestion Énergétique** : Suivi de la consommation pour détecter les anomalies électriques.
* **Remote Control** : Arrêt d'urgence à distance via un système de communication Sockets (TCP/IP).

---

## 📊 Aperçu de l'Interface (IHM)

| État Nominal (Normal) | État Critique (Alerte) |
| :--- | :--- |
| ![Nominal State](./screenshots/ihm1.png) | ![Alert State](./screenshots/ihm2.png) |
| *Température stable et monitoring au vert.* | *Détection de surchauffe et déclenchement de l'alerte.* |

---

## 🏗️ Architecture du Projet

Le projet est structuré selon une approche modulaire pour assurer la scalabilité :

1.  **Simulation** (`Machine.java`) : Génération de flux de données réalistes.
2.  **Ingestion** (`DataIngestionService`) : API REST sous Spring Boot pour la collecte de données.
3.  **Intelligence** (`PredictiveMaintenanceML`) : Modèle de diagnostic basé sur l'IA.
4.  **Contrôle** (`RemoteControlServer`) : Pilotage à distance sécurisé.

---

## 🛠️ Installation & Lancement

1.  **Cloner le projet** :
    ```bash
    git clone [https://github.com/votre-username/induscore.git](https://github.com/votre-username/induscore.git)
    ```
2.  **Configuration** : Assurez-vous d'avoir le JDK 17+ et Maven installés.
3.  **Lancer le Backend** : Exécuter `DataIngestionService.java` (Spring Boot).
4.  **Lancer l'Interface** : Exécuter `MachineMonitoringGUI.java`.

---

## 👨‍💻 Auteur
**[yasser oueslati]
