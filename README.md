# Human Biomechanic Analysis
# Deep Learning Models for the Early Detection of Parkinson’s Disease using the motor-based symptoms.

## Abstract
The old age population is commonly affected with gait and balance disorders such as Parkinson’s disease (PD). PD is a progressive neurodegenerative movement disorder caused by the degeneration of substantia nigra dopaminergic neurons present in basal ganglia. Parkinson’s Disease (PD) is a disorder of the central nervous system that affects movement, often including tremors. 

We have designed a system that uses **deep learning to detect Parkinson’s Disease at an earlier stage**. Using the motor-based inputs we have developed deep learning models which detects motor-based symptoms in the subject’s body to subsequently detect the onset of Parkinson’s disease and also the severity of the Parkinson’s Disease based on the Hoehn Yahr Severity Scale.

---

## Motivation
According to the Parkinson Disease Foundation, one million Americans are living with Parkinson disease, and approximately 60,000 Americans are diagnosed with Parkinson disease each year. Similarly, 1.2 million Europeans suffer from it, and this number is forecasted to double by 2030.

The **early detection of Parkinson’s Disease** is a clinical challenge for the scientific community. Currently, no cure is available for the disease or the symptoms; therefore, movement therapy is important to delay the loss of motor function. Patients generally are clearly diagnosed with PD at the advanced stage; moreover, any neuroprotective therapy initiated at such a late stage may have fewer substantial effects on the disease progression. The Deep Learning Models can help in the early identification of Parkinson's Disease using the motor and non-motor symptoms, so that patients can receive the proper treatment and advice regarding care.

---

## Datasets
### [**Physionet Dataset**](https://physionet.org/content/gaitpdb/1.0.0/)
  - This database contains measures of gait from: 
    -	93 patients with PD (mean age: 66.3 years; 63% men)
    -	73 healthy controls (mean age: 66.3 years; 55% men)
  -	The database includes the vertical ground reaction force records of subjects as they walked at their usual, self-selected pace for approximately 2 minutes on level ground.
  -	The output of each of these 16 sensors has been digitized and recorded at **100 samples per second**, and the records also include two signals that reflect the sum of the 8 sensor outputs for each foot. 

### [**Daphnet Dataset**](https://archive.ics.uci.edu/ml/datasets/Daphnet+Freezing+of+Gait)
  - The dataset comprises of recordings of 3D acceleration at 64 Hz from 3 acceleration sensors. 
  - The sensors are placed at the ankle (shank), on the thigh, and on the hip. The dataset was recorded in the lab with emphasis on generating many freeze events. 
  - Users performed three kinds of tasks: straight line walking, walking with numerous turns, and finally a more realistic activity of daily living (ADL) task, where users went into different rooms while fetching coffee, opening doors, etc. The meaning of the annotations of the samples are as follows:
    -	**0**: not part of the experiment. For instance, the sensors are installed on the user or the user is performing activities unrelated to the experimental protocol, such as debriefing.
    - **1**: experiment, no freeze (can be any of stand, walk, turn)
    - **2**: freeze

Physionet - Sensor Positions         |  Daphnet - Sensor Positions
:-------------------------:|:-------------------------:
<img src="https://user-images.githubusercontent.com/53406309/154796873-dace410a-3350-4177-9134-97871521e2de.png" width="70%"> | <img src="https://user-images.githubusercontent.com/53406309/154796892-aa350d45-9b3a-47ef-bb8b-5e7245087b17.png" width="70%">

---

## System Architecture
![image](https://user-images.githubusercontent.com/53406309/154796525-48cda3ab-e596-475f-8bd5-569ec2f44841.png)

1. **Input from 8 force sensors under each foot**
    - The 8 force sensors are placed under each foot (16 force sensors) that measure the vertical ground reaction force at 100 Hz.

1.	**Input from 3 acceleration sensors**
    - The 3 acceleration sensors are placed at Trunk, Upper Leg (Thigh) and Ankle (Shank) that measure the acceleration in 3 channels (x, y and z axis) at 64 Hz.

1.	**CNN for Detection of Parkinson’s Disease**
    - Based on the data from 16 force sensors, Convolutional Neural Network is used for the Detection of Parkinson’s Disease.

1.	**CNN for Classification of Parkinson’s Disease Severity**
    - Based on the data from 16 force sensors, Convolutional Neural Network is used for the Classification of Parkinson’s Disease Severity.

1.	**CNN for Detection of Freezing of GAIT**
    - Based on the data from 3 acceleration sensors, Convolutional Neural Network is used for the Detection of Freezing of GAIT.

1.	**Final Output**
    - The Detection of Parkinson’s Disease model and Freezing GAIT models are combined together using voting method to produce the Final Output. 
    - The Classification model produces the Parkinson’s Disease Severity Based on the Hoehn Yahr Scale.


### Parkinson's Disease Detection Model
Based on the data from 16 force sensors, the Parkinson’s Disease Detection model is shown below. The output of this model is the detection of the presence of PD in the patients.

![PD Detection Model](https://user-images.githubusercontent.com/53406309/154798470-27f92033-a713-44c5-b55b-c51190ea5c19.png)

### Parkinson's Disease Severity Classification Model
Based on the data from 16 force sensors, the Parkinson’s Disease Severity Classification model is shown below. The output of this model is the classification of the severity of PD in the patients based on the Hoehn Yahr (HY) scale.

![PD Serverity Classification Model](https://user-images.githubusercontent.com/53406309/154798491-00ad630a-2a75-440b-a8b2-f51d9910b109.png)

### Freezing of Gait Detection Model
Based on the data from 3 acceleration sensors, the Freezing of GAIT Detection Model is shown below. The output of this model is the detection of the presence of Freezing of GAIT (FOG) in the patients.

![FOG Detection Model](https://user-images.githubusercontent.com/53406309/154798532-0e28ba5f-2b79-44cb-aa6a-70933e07b650.png)

### Final Prediction by Voting
- The Parkinson’s Disease Detection Model and Freezing of Gait Detection Model are combined together for the Detection of Parkinson’s Disease using the voting technique. 
- The severity of Parkinson's Disease is given by the Parkinson's Disease Severity Classification Model based on the Hoehn Yahr Severity Scale.
- Since the final output is based on two models, the combined final prediction will be detecting the presence of Parkinson’s Disease more accurately.

---

## PD DETECT API
- The API is hosted in Heroku. Link: https://pd-detect-api.herokuapp.com/
- This API returns sample data in JSON format from the test dataset.
- To view the JSON data, visit https://pd-detect-api.herokuapp.com/finaldetect

---

## Android Applications
###  PD DETECT
- PD Detect is a mobile application developed for the Detection of Parkinson’s Disease and for assessing the severity of the Parkinson’s Disease based on Hoehn Yahr Scale and few survey questions. 
- This application detects the Parkinson's disease using the output from 3 accelerometers placed at the ankle (shank), on the thigh, and on the hip & 16 sensors measuring force (in Newton) placed at the feet. The data is fetched from the API.
- There are 12 severity assessment questions which are also combined together with the model to predict the severity.

Loading Page        |  Home Page
:-------------------------:|:-------------------------:
<img src="https://user-images.githubusercontent.com/53406309/154800026-55210992-51a9-4ef1-afaa-6eeb3b864a3c.png" width="70%"> | <img src="https://user-images.githubusercontent.com/53406309/154800029-679c3980-3351-4461-bc60-f63a5edd68d5.png" width="70%">

PD Detected        |  Severity Assessment Questions
:-------------------------:|:-------------------------:
<img src="https://user-images.githubusercontent.com/53406309/154800051-16a49ffc-2251-4f1f-ac7f-297d4a3202a8.png" width="70%"> | <img src="https://user-images.githubusercontent.com/53406309/154800053-5002694a-c7fc-4139-ba25-dae1e8514022.png" width="70%">

PD Severity Result       |  No PD Detected
:-------------------------:|:-------------------------:
<img src="https://user-images.githubusercontent.com/53406309/154800107-c0dc30d3-fe28-4559-ba32-aeca0fa87b5e.png" width="70%"> | <img src="https://user-images.githubusercontent.com/53406309/154800108-2a168551-86b8-4b76-b17a-d337b99bdb08.png" width="70%">

### ParkinFit
- ParkinFit mobile application calculates the Modified UPRS score for Parkinson's Disease based on the severity assessment question.
- This app doesn't use any of the models for PD Detection.

Home Page       |  Profile
:-------------------------:|:-------------------------:
<img src="https://user-images.githubusercontent.com/53406309/154800934-18af7bd8-d3bd-4359-9a07-deff2b574f87.png" width="70%"> | <img src="https://user-images.githubusercontent.com/53406309/154800936-e8fe2dac-1565-411c-85cc-4db8890b9ddb.png" width="70%">


Severity Assessment Questions   | Result Page
:-------------------------:|:-------------------------:
<img src="https://user-images.githubusercontent.com/53406309/154801002-46b3b46b-b071-4e24-b130-894713b7821f.png" width="70%"> | <img src="https://user-images.githubusercontent.com/53406309/154800999-d71cdba1-8d7c-4513-ba4b-b2d478913637.png" width="70%">

---

### Note
This project is done by a team of 5 students as a part of Project Work in CSE.
