package com.example.data.repository

import com.example.data.model.*

object SyllabusData {

    val subjects = SubjectType.values().toList()

    val chapters: List<Chapter> = listOf(
        // BENGALI
        Chapter(
            id = "bn_ch1",
            subjectId = "bengali",
            chapterNumber = 1,
            titleBn = "জ্ঞানচক্ষু (গল্প - আশাপূর্ণা দেবী)",
            titleEn = "Gyanchakshu (Ashapurna Debi)",
            marksWeightage = "MCQ: 1 | VSA: 1 | SA: 3 | LA: 5",
            summary = "তপনের গল্প লেখার স্বপ্ন এবং ছোটমেসোর মাধ্যমে পত্রিকায় প্রকাশের পর তার আত্মোপলব্ধির কাহিনী। তপনের প্রকৃত জ্ঞানচক্ষু উন্মোচিত হওয়ার মর্মস্পর্শী পাঠ।",
            keyTopics = listOf("তপনের স্বপ্ন ও গল্প রচনা", "ছোটমেসোর ভূমিকা ও সংশোধন", "সন্ধ্যাতারা পত্রিকায় প্রকাশ", "তপনের আত্মমর্যাদা ও প্রকৃত জ্ঞানচক্ষু লাভ")
        ),
        Chapter(
            id = "bn_ch2",
            subjectId = "bengali",
            chapterNumber = 2,
            titleBn = "অসুখী একজন (কবিতা - পাবলো নেরুদা)",
            titleEn = "Asukhi Ekjon (Pablo Neruda)",
            marksWeightage = "MCQ: 1 | VSA: 1 | SA: 3",
            summary = "যুদ্ধের ভয়াবহতা, ধ্বংসলীলা এবং ভালোবাসার চিরন্তন প্রতীক্ষার পটভূমিতে লেখা চিলির বিখ্যাত কবির কবিতা। মেয়েটি যে অপেক্ষা করে রইল ভালোবাসার প্রতীক হিসেবে।",
            keyTopics = listOf("যুদ্ধের পটভূমি ও ধ্বংস", "মেয়েটির নিরন্তর প্রতীক্ষা", "রক্তের এক কালো দাগ", "চিরন্তন ভালোবাসার জয়")
        ),
        Chapter(
            id = "bn_ch3",
            subjectId = "bengali",
            chapterNumber = 3,
            titleBn = "বহুরূপী (গল্প - সুবোধ ঘোষ)",
            titleEn = "Bohurupi (Subodh Ghosh)",
            marksWeightage = "MCQ: 1 | VSA: 1 | SA: 3 | LA: 5",
            summary = "হরিদা নামের এক দরিদ্র মানুষের বৈচিত্র্যময় বহুরূপী সাজ ও তাঁর শিল্পীমনের সততা। জগদীশবাবুর দেওয়া টাকা না নিয়ে হরিদার নিঃস্বার্থ শিল্পবোধের পরিচয়।",
            keyTopics = listOf("হরিদার দারিদ্র্য ও বহুরূপীর পেশা", "বাইজি ও বিরাগী রূপের বর্ণনা", "জগদীশবাবুর বাড়িতে বিরাগী সাজ", "হরিদার আত্মমর্যাদা ও শিল্পপ্রেম")
        ),
        Chapter(
            id = "bn_ch4",
            subjectId = "bengali",
            chapterNumber = 4,
            titleBn = "সিরাজউদ্দৌলা (নাটক - শচীন্দ্রনাথ সেনগুপ্ত)",
            titleEn = "Sirajuddoula (Sachindranath Sengupta)",
            marksWeightage = "LA: 4/5 | SA: 3",
            summary = "পলাশীর যুদ্ধের প্রাক্কালে নবাব সিরাজউদ্দৌলার স্বদেশপ্রেম, সভাসদদের বিশ্বাসঘাতকতা ও জাতীয় সংকট মোচনে আকুল আবেদনের ঐতিহাসিক দৃশ্য।",
            keyTopics = listOf("নবাব সিরাজের দেশপ্রেম", "মীরজাফর ও ঘসেটি বেগমের ষড়যন্ত্র", "সভাসদদের প্রতি সিরাজের আহ্বান", "স্বাধীনতা রক্ষার অন্তিম আকুতি")
        ),
        Chapter(
            id = "bn_ch5",
            subjectId = "bengali",
            chapterNumber = 5,
            titleBn = "ব্যাকরণ: কারক ও অকারক সম্পর্ক এবং সমাস",
            titleEn = "Bengali Grammar: Karaka and Samasa",
            marksWeightage = "MCQ: 4 | VSA: 4",
            summary = "কারকের প্রকারভেদ, বিভক্তি ও অনুসর্গ এবং দ্বন্দ, কর্মধারয়, তৎপুরুষ, বহুব্রীহি ও দ্বিগু সমাসের ব্যাসবাক্যসহ বিস্তারিত নিয়ম।",
            keyTopics = listOf("কর্তৃ, কর্ম, করণ, অপাদান, অধিকরণ কারক", "সম্বন্ধ ও সম্বোধন পদ", "তৎপুরুষ, কর্মধারয়, বহুব্রীহি সমাস", "ব্যাসবাক্য নির্ণয় কৌশল")
        ),

        // ENGLISH
        Chapter(
            id = "en_ch1",
            subjectId = "english",
            chapterNumber = 1,
            titleBn = "Father's Help (R. K. Narayan)",
            titleEn = "Father's Help",
            marksWeightage = "Seen Comprehension: 10 Marks",
            summary = "Swami's reluctance to go to school on a Monday morning, excuses made against Samuel, father's strict challenge, and Swami's realization of Samuel's genuine kindness.",
            keyTopics = listOf("Swami's headache excuse", "Father's letter to the Headmaster", "Swami's guilty conscience", "Samuel's unexpected gentle behavior")
        ),
        Chapter(
            id = "en_ch2",
            subjectId = "english",
            chapterNumber = 2,
            titleBn = "Fable (Ralph Waldo Emerson)",
            titleEn = "Fable (Poem)",
            marksWeightage = "Seen Poem: 5 Marks",
            summary = "The philosophical conversation between a mountain and a squirrel highlighting that every being has its own talent and unique significance in nature.",
            keyTopics = listOf("Dispute between mountain and squirrel", "Bun's moral argument", "Talents differ in God's world", "Carrying forests vs cracking a nut")
        ),
        Chapter(
            id = "en_ch3",
            subjectId = "english",
            chapterNumber = 3,
            titleBn = "The Passing Away of Bapu (Nayantara Sehgal)",
            titleEn = "The Passing Away of Bapu",
            marksWeightage = "Seen Prose: 10 Marks",
            summary = "The poignant recounting of Mahatma Gandhi's assassination, India's profound grief at Birla House, the historic funeral procession, and continuing his ideals.",
            keyTopics = listOf("Urgent telephone call to author", "Grief-stricken crowd at Birla House", "Funeral procession through Delhi", "Carrying forward Bapu's legacy")
        ),
        Chapter(
            id = "en_ch4",
            subjectId = "english",
            chapterNumber = 4,
            titleBn = "Writing Skills (Notice, Report, Letter, Processing)",
            titleEn = "Madhyamik English Writing Skills",
            marksWeightage = "Writing: 30 Marks (3 x 10)",
            summary = "Comprehensive formats for Newspaper Reports, Formal/Informal Letters, School Notices, and Flow-chart Process writings according to WBBSE marking guidelines.",
            keyTopics = listOf("Newspaper Report format with headlines", "Official & Editorial Letter layout", "School Notice writing structure", "Process flowchart to paragraph")
        ),

        // MATHEMATICS
        Chapter(
            id = "math_ch1",
            subjectId = "math",
            chapterNumber = 1,
            titleBn = "একচলবিশিষ্ট দ্বিঘাত সমীকরণ (অধ্যায় ১)",
            titleEn = "Quadratic Equation in One Variable",
            marksWeightage = "MCQ: 1 | SA: 2 | LA: 3 + 3",
            summary = "ax² + bx + c = 0 সমীকরণের সমাধান, শ্রীধর আচার্যের সূত্র, নিরূপক (b² - 4ac) দ্বারা বীজের প্রকৃতি নির্ণয় এবং বাস্তব সমস্যায় দ্বিঘাত সমীকরণ গঠন।",
            keyTopics = listOf("সমীকরণ গঠন ও উৎপাদকে বিশ্লেষণ", "শ্রীধর আচার্যের সূত্রের প্রয়োগ", "নিরূপক ও বীজের প্রকৃতি (বাস্তব, সমান, অসমান)", "বাস্তব জীবনভিত্তিক দ্বিঘাত সমস্যা")
        ),
        Chapter(
            id = "math_ch2",
            subjectId = "math",
            chapterNumber = 2,
            titleBn = "সরল সুদকষা (অধ্যায় ২)",
            titleEn = "Simple Interest",
            marksWeightage = "MCQ: 1 | SA: 2 | LA: 5",
            summary = "I = (P × r × t) / 100 সূত্রের সাহায্যে আসল, সুদের হার, সময় ও সবৃদ্ধিমূল সম্পর্কিত পাটিগণিতের জটিল গাণিতিক সমস্যার সমাধান।",
            keyTopics = listOf("আসল (P), বার্ষিক সুদের হার (r), সময় (t)", "সবৃদ্ধিমূল (A = P + I)", "বিভিন্ন ব্যাংকে টাকা বিভাজনের প্রশ্ন", "মোট সুদের অনুপাত সংক্রান্ত অংক")
        ),
        Chapter(
            id = "math_ch3",
            subjectId = "math",
            chapterNumber = 3,
            titleBn = "বৃত্ত সম্পর্কিত উপপাদ্য (উপপাদ্য ৩২ ও ৩৩)",
            titleEn = "Theorems Related to Circle",
            marksWeightage = "Theorem: 5 Marks | Rider: 3 Marks",
            summary = "বৃত্তের কেন্দ্র ও জ্যা সংক্রান্ত গুরুত্বপূর্ণ উপপাদ্য: বৃত্তের কেন্দ্রগামী কোনো সরলরেখা জ্যাকে সমদ্বিখণ্ডিত করলে তা লম্ব হবে এবং এর বিপরীত উপপাদ্য।",
            keyTopics = listOf("উপপাদ্য ৩২ প্রমাণ", "উপপাদ্য ৩৩ প্রমাণ", "জ্যা ও লম্ব দূরত্বের জ্যামিতিক প্রয়োগ", "বৃত্তস্থ উপপাদ্য সংক্রান্ত প্রয়োগ (Rider)")
        ),
        Chapter(
            id = "math_ch4",
            subjectId = "math",
            chapterNumber = 4,
            titleBn = "আয়তঘন ও ঘনক (অধ্যায় ৪)",
            titleEn = "Rectangular Parallelopiped and Cube",
            marksWeightage = "MCQ: 1 | SA: 2 | LA: 4",
            summary = "আয়তঘনের সমগ্রতলের ক্ষেত্রফল = 2(lb + bh + hl), আয়তন = lbh এবং কর্ণের দৈর্ঘ্য = √(l² + b² + h²)। ঘনকের সূত্র ও সম্পর্কিত বাস্তব সমস্যা।",
            keyTopics = listOf("আয়তঘনের ক্ষেত্রফল ও আয়তন", "কর্ণের সূত্র ও বৃহত্তম দণ্ড রাখার নিয়ম", "ঘনকের ধার পরিবর্তন হলে আয়তনের অনুপাত", "মিশ্র বাস্তব পরিমিতির প্রশ্ন")
        ),
        Chapter(
            id = "math_ch5",
            subjectId = "math",
            chapterNumber = 5,
            titleBn = "অনুপাত ও সমানুপাত এবং দ্বিঘাত করণী",
            titleEn = "Ratio, Proportion & Quadratic Surds",
            marksWeightage = "LA: 3 + 3 Marks",
            summary = "k-পদ্ধতির সাহায্যে সমানুপাতের প্রমাণ এবং করণী নিরসন, অনুবন্ধী করণী ও মান নির্ণয়ের নিশ্চিত পরীক্ষায় আসার মতো অংক।",
            keyTopics = listOf("যোগ-ভাগ ও ভাগ-যোগ প্রক্রিয়া", "k-পদ্ধতির দ্বারা বীজগণিত প্রমাণ", "করণী নিরসক উৎপাদক", "অনুবন্ধী করণীর বৈশিষ্ট্য ও সরলীকরণ")
        ),

        // PHYSICAL SCIENCE
        Chapter(
            id = "phy_ch1",
            subjectId = "phy_sci",
            chapterNumber = 1,
            titleBn = "পরিবেশের জন্য ভাবনা (অধ্যায় ১)",
            titleEn = "Concern About Our Environment",
            marksWeightage = "MCQ: 1 | VSA: 2 | SA: 2",
            summary = "বায়ুমণ্ডলের স্তরবিন্যাস (ট্রপোস্ফিয়ার থেকে এক্সোস্ফিয়ার), ওজোন স্তর ধ্বংস ও সিএফসি-র ভূমিকা, গ্রিনহাউস এফেক্ট ও অপ্রচলিত শক্তি উৎস।",
            keyTopics = listOf("বায়ুমণ্ডলের স্তর ও উচ্চতা পরিবর্তনের সাথে উষ্ণতা", "ওজোন স্তরের ক্ষয় ও ওজোন হোল", "গ্লোবাল ওয়ার্মিং ও মিথেন হাইড্রেট (ফায়ার আইস)", "বায়োফুয়েল ও সৌরশক্তি ব্যবহার")
        ),
        Chapter(
            id = "phy_ch2",
            subjectId = "phy_sci",
            chapterNumber = 2,
            titleBn = "গ্যাসের আচরণ (অধ্যায় ২)",
            titleEn = "Behavior of Gases",
            marksWeightage = "MCQ: 1 | VSA: 2 | LA: 3 (Problem/Proof)",
            summary = "বয়েলের সূত্র (P₁V₁ = P₂V₂), চার্লসের সূত্র (V₁/T₁ = V₂/T₂), পরম শূন্য উষ্ণতা (-273°C), আদর্শ গ্যাস সমীকরণ (PV = nRT) ও গ্যাসের গতীয় তত্ত্ব।",
            keyTopics = listOf("বয়েল ও চার্লসের সূত্রের সমন্বয়", "PV = (W/M)RT সমীকরণ থেকে আণবিক ভর", "পরম শূন্য উষ্ণতা ও কেলভিন স্কেল", "গ্যাসের গতিতত্ত্বের মৌলিক স্বীকার্য")
        ),
        Chapter(
            id = "phy_ch3",
            subjectId = "phy_sci",
            chapterNumber = 3,
            titleBn = "আলো (প্রতিসরণ, লেন্স ও বর্ণালী)",
            titleEn = "Light: Refraction, Lenses and Dispersion",
            marksWeightage = "MCQ: 2 | SA: 2 | LA: 3 + 3 (Total 12)",
            summary = "গোলীয় দর্পণে প্রতিফলন, স্নেলের সূত্র ও প্রতিসরাঙ্ক, প্রিজমে চ্যুতি (δ = i₁ + i₂ - A), পাতলা লেন্স (1/v - 1/u = 1/f), চোখের দৃষ্টিত্রুটি ও রামধনু।",
            keyTopics = listOf("গোলীয় দর্পণে f = R/2 প্রমাণ", "প্রিজমের ক্ষেত্রে চ্যুতি কোণের রাশিমালা", "উত্তল ও অবতল লেন্সের প্রতিবিম্ব গঠন", "হ্রস্বদৃষ্টি (মায়োপিয়া) ও দীর্ঘদৃষ্টি প্রতিকার")
        ),
        Chapter(
            id = "phy_ch4",
            subjectId = "phy_sci",
            chapterNumber = 4,
            titleBn = "চলতড়িৎ (ওহমের সূত্র ও তড়িৎপ্রবাহের তাপীয় ফল)",
            titleEn = "Current Electricity",
            marksWeightage = "MCQ: 2 | SA: 2 | LA: 3 + 3 (Total 12)",
            summary = "তড়িৎবিভব, ওহমের সূত্র (V = IR), রোধাঙ্ক, রোধের শ্রেণী ও সমান্তরাল সমবায়, জুলের তাপীয় সূত্র (H = I²Rt / J), B.O.T ইউনিট ও ফ্লেমিংয়ের বামহস্ত নিয়ম।",
            keyTopics = listOf("ওহমের সূত্রের গাণিতিক রূপ ও রোধাঙ্ক", "রোধের সমান্তরাল ও শ্রেণী সমবায়ের তুল্যরোধ", "জুলের সূত্র ও বৈদ্যুতিক বাল্বের হিসাব", "B.O.T ও কিলোওয়াট-ঘণ্টা হিসাব")
        ),
        Chapter(
            id = "phy_ch5",
            subjectId = "phy_sci",
            chapterNumber = 5,
            titleBn = "পর্যায় সারণি ও মৌলদের ধর্মের পর্যায়বৃত্ততা",
            titleEn = "Periodic Table and Periodicity",
            marksWeightage = "MCQ: 2 | VSA: 2 | SA: 2",
            summary = "মেন্ডেলিফের পর্যায় সূত্র ও আধুনিক পর্যায় সূত্র, পর্যায় ও শ্রেণীবিন্যাস, পারমাণবিক ব্যাসার্ধ, আয়নন বিভব, তড়িৎ-ঋণাত্মকতা ও জারণ-বিজারণ ধর্মের পর্যায়ক্রমিক পরিবর্তন।",
            keyTopics = listOf("আধুনিক পর্যায় সূত্র (পারমাণবিক সংখ্যা ভিত্তি)", "আয়নাইজেশন শক্তি ও তড়িৎঋণাত্মকতা", "হ্যালোজেন ও ক্ষারধাতুর অবস্থান", "হাইড্রোজেনের দ্বৈত আচরণ")
        ),

        // LIFE SCIENCE
        Chapter(
            id = "life_ch1",
            subjectId = "life_sci",
            chapterNumber = 1,
            titleBn = "জীবজগতের নিয়ন্ত্রণ ও সমন্বয় (অধ্যায় ১)",
            titleEn = "Control and Coordination in Living Organisms",
            marksWeightage = "MCQ: 3 | VSA: 3 | SA: 2 | LA: 5 + 5 (Total 19)",
            summary = "উদ্ভিদের চলন (ট্রপিক ও ন্যাস্টিক), উদ্ভিদ হরমোন (অক্সিন, জিব্বেরেলিন, সাইটোকাইনিন), মানবদেহের অন্তঃক্ষরা গ্রন্থি, নিউরোনের গঠন, প্রতিবর্ত ক্রিয়া এবং মানুষের চোখের গঠন।",
            keyTopics = listOf("অক্সিনের ভূমিকা ও ট্রপিক চলন", "থাইরক্সিন, ইনসুলিন ও অ্যাড্রিনালিন হরমোন", "নিউরোনের চিহ্নিত চিত্র ও সিন্যাপ্স", "মানুষের অক্ষিগোলকের চিহ্নিত চিত্র (৫ নম্বর নিশ্চিত)")
        ),
        Chapter(
            id = "life_ch2",
            subjectId = "life_sci",
            chapterNumber = 2,
            titleBn = "জীবনের প্রবাহমানতা (অধ্যায় ২ - কোষ বিভাজন ও জনন)",
            titleEn = "Continuity of Life: Cell Division & Reproduction",
            marksWeightage = "MCQ: 3 | VSA: 3 | SA: 2 | LA: 5 (Total 17)",
            summary = "ক্রোমোজোমের গঠন (DNA ও হিস্টোন প্রোটিন), মাইটোসিস কোষ বিভাজনের মেটাফেজ ও অ্যানাফেজ দশা, মিয়োসিসের তাৎপর্য, উদ্ভিদের অযৌন ও যৌন জনন এবং সপুষ্পক উদ্ভিদের দ্বিনিষেক।",
            keyTopics = listOf("আদর্শ ইউক্যারিওটিক ক্রোমোজোমের চিত্র", "মাইটোসিসের মেটাফেজ ও অ্যানাফেজ চিত্র", "অযৌন ও যৌন জননের পার্থক্য", "সপুষ্পক উদ্ভিদের নিষেধ ও ভ্রূণ গঠন")
        ),
        Chapter(
            id = "life_ch3",
            subjectId = "life_sci",
            chapterNumber = 3,
            titleBn = "বংশগতি এবং কয়েকটি সাধারণ জিনগত রোগ (অধ্যায় ৩)",
            titleEn = "Heredity and Common Genetic Diseases",
            marksWeightage = "MCQ: 3 | VSA: 2 | SA: 2 | LA: 5 (Total 15)",
            summary = "মেন্ডেলের একসংকর ও দ্বিসংকর জনন পরীক্ষা ও সূত্র, ফিনোটাইপ ও জিনোটাইপ অনুপাত (৩:১ ও ৯:৩:৩:১), মানুষের লিঙ্গ নির্ধারণ এবং থ্যালাসেমিয়া, বর্ণান্ধতা ও হিমোফিলিয়া।",
            keyTopics = listOf("মেন্ডেলের দ্বিসংকর জননের চেকারবোর্ড", "পৃথকভবন ও স্বাধীন সঞ্চারণ সূত্র", "থ্যালাসেমিয়ার কারণ ও জেনেটিক কাউন্সেলিং", "মানুষের লিঙ্গ নির্ধারণে পিতার ভূমিকা")
        ),
        Chapter(
            id = "life_ch4",
            subjectId = "life_sci",
            chapterNumber = 4,
            titleBn = "অভিব্যক্তি ও অভিযোজন (অধ্যায় ৪)",
            titleEn = "Evolution and Adaptation",
            marksWeightage = "MCQ: 2 | VSA: 2 | SA: 2 | LA: 5 (Total 15)",
            summary = "ল্যামার্কবাদ ও ডারউইনবাদ, সমসংস্থ ও সমবৃত্তীয় অঙ্গ, ক্যাকটাস ও সুন্দরী গাছের অভিযোজন, পায়রার বায়ুতলি ও মৌমাছির ওয়াগল নৃত্য।",
            keyTopics = listOf("ডারউইনের প্রাকৃতিক নির্বাচন তত্ত্ব", "সমসংস্থ ও সমবৃত্তীয় অঙ্গের তুলনা", "সুন্দরী গাছের লবণ সহনশীলতা ও শ্বাসমূল", "পায়রার খেচর অভিযোজনে বায়ুতলির ভূমিকা")
        ),

        // HISTORY
        Chapter(
            id = "hist_ch1",
            subjectId = "history",
            chapterNumber = 1,
            titleBn = "ইতিহাসের ধারণা (অধ্যায় ১)",
            titleEn = "Ideas of History",
            marksWeightage = "MCQ: 2 | VSA: 2 | SA: 2 (Total 6)",
            summary = "নতুন সামাজিক ইতিহাস চর্চা, খেলাধুলার ইতিহাস, পোশাক-পরিচ্ছদ, খাদ্যাভ্যাস, পরিবেশ ও নারীর ইতিহাস। সোমপ্রকাশ ও বঙ্গদর্শন পত্রিকার গুরুত্ব।",
            keyTopics = listOf("নতুন সামাজিক ইতিহাস চর্চার বৈশিষ্ট্য", "পরিবেশের ইতিহাস ও নারী ইতিহাস চর্চা", "সোমপ্রকাশ ও বামাবোধিনী পত্রিকা", "ইতিহাসের উপাদান হিসেবে ইন্টারনেটের সুবিধা-অসুবিধা")
        ),
        Chapter(
            id = "hist_ch2",
            subjectId = "history",
            chapterNumber = 2,
            titleBn = "সংস্কার: বৈশিষ্ট্য ও পর্যালোচনা (অধ্যায় ২)",
            titleEn = "Reform: Characteristics and Observations",
            marksWeightage = "MCQ: 2 | VSA: 2 | SA: 2 | LA: 4/8",
            summary = "উনিশ শতকের বাংলার নবজাগরণ, ব্রাহ্ম আন্দোলন ও রাজা রামমোহন রায়, বিদ্যাসাগরের বিধবাবিবাহ আন্দোলন, উডের ডেসপ্যাচ ও প্রাচ্য-পাশ্চাত্য শিক্ষা বিতর্ক।",
            keyTopics = listOf("শ্রীরামকৃষ্ণ পরমহংসদেবের সর্বধর্ম সমন্বয়", "বিদ্যাসাগরের নারীশিক্ষা ও বিধবাবিবাহ উদ্যোগ", "ব্রাহ্ম আন্দোলনের বিকাশ ও বিভাজন", "উডের ডেসপ্যাচ (১৮৫৪) ও পাশ্চাত্য শিক্ষার প্রসার")
        ),
        Chapter(
            id = "hist_ch3",
            subjectId = "history",
            chapterNumber = 3,
            titleBn = "প্রতিরোধ ও বিদ্রোহ: বৈশিষ্ট্য ও বিশ্লেষণ (অধ্যায় ৩)",
            titleEn = "Resistance and Rebellion: Characteristics & Analysis",
            marksWeightage = "MCQ: 2 | VSA: 2 | SA: 2 | LA: 4/8",
            summary = "চুয়াড় বিদ্রোহ, কোল বিদ্রোহ, সাঁওতাল বিদ্রোহ (১৮৫৫), ওয়াহাবি ও ফরায়েজি আন্দোলন এবং নীল বিদ্রোহ (১৮৫৯-৬০)। বিদ্রোহীদের কারণ ও ইংরেজদের দমননীতি।",
            keyTopics = listOf("সাঁওতাল বিদ্রোহের প্রধান কারণ ও সিধু-কানহু", "নীল বিদ্রোহের চরিত্র ও শিক্ষিত মধ্যবিত্তের ভূমিকা", "ফরায়েজি আন্দোলনের হাজী শরীয়তুল্লাহ", "মুন্ডা বিদ্রোহ ও বিরসা মুন্ডার নেতৃত্ব")
        ),
        Chapter(
            id = "hist_ch4",
            subjectId = "history",
            chapterNumber = 4,
            titleBn = "সংঘবদ্ধতার গোড়ার কথা (১৮৫৭-র মহাবিদ্রোহ ও জাতীয়তাবাদ)",
            titleEn = "Early Stages of Collective Action",
            marksWeightage = "MCQ: 2 | VSA: 2 | SA: 2 | LA: 4/8",
            summary = "১৮৫৭ খ্রিস্টাব্দের মহাবিদ্রোহের প্রকৃতি ও চরিত্র, মহারানীর ঘোষণাপত্র (১৮৫৮), হিন্দু মেলা, ভারত সভা এবং আনন্দমঠ উপন্যাস ও ভারতমাতা চিত্রের জাতীয়তাবাদী অবদান।",
            keyTopics = listOf("১৮৫৭-র বিদ্রোহের চরিত্র: সিপাহি বিদ্রোহ না জাতীয় সংগ্রাম", "অবনীন্দ্রনাথ ঠাকুরের 'ভারতমাতা' চিত্রের জাতীয় চেতনা", "আনন্দমঠ ও বন্দে মাতরম্ সংগীতের ভূমিকা", "সুরেন্দ্রনাথ ব্যানার্জী ও ভারত সভার অবদান")
        ),

        // GEOGRAPHY
        Chapter(
            id = "geo_ch1",
            subjectId = "geography",
            chapterNumber = 1,
            titleBn = "বহির্জাত প্রক্রিয়া ও তাদের দ্বারা সৃষ্ট ভূমিরূপ (অধ্যায় ১)",
            titleEn = "Exogenetic Processes and Resultant Landforms",
            marksWeightage = "MCQ: 2 | VSA: 2 | SA: 2 | LA: 5 (Total 14)",
            summary = "নদী, হিমবাহ ও বায়ুর ক্ষয়কার্য ও সঞ্চয়কার্যের ফলে সৃষ্ট ভূমিরূপ (আই ও ভি আকৃতির উপত্যকা, জলপ্রপাত, অশ্বখুরাকৃতি হ্রদ, ঝুলন্ত উপত্যকা, গ্রাবরেখা, বার্খান, ইনসেলবার্জ)।",
            keyTopics = listOf("নদীর ক্ষয়কাজের ৩টি ভূমিরূপ চিত্রসহ", "হিমবাহ ও জলধারার মিলিত সঞ্চয়কাজ (এসকার, কেম)", "বায়ু ও জলধারার মিলিত কার্যে পেডিমেন্ট ও বাজাদা", "ব-দ্বীপ গঠনের অনুকূল ভৌগোলিক পরিবেশ")
        ),
        Chapter(
            id = "geo_ch2",
            subjectId = "geography",
            chapterNumber = 2,
            titleBn = "বায়ুমণ্ডল ও বারিমণ্ডল (অধ্যায় ২ ও ৩)",
            titleEn = "Atmosphere and Hydrosphere",
            marksWeightage = "MCQ: 3 | VSA: 3 | SA: 2 | LA: 5 (Total 14)",
            summary = "বায়ুমণ্ডলের উষ্ণতার তারতম্যের কারণ, নিয়ত বায়ুপ্রবাহ ও জলবায়ু পরিবর্তন, সমুদ্রস্রোতের কারণ ও ফলাফল এবং ভরা কোটাল ও মরা কোটাল।",
            keyTopics = listOf("বায়ুমণ্ডলে উষ্ণতার তারতম্যের তিনটি কারণ", "ঘূর্ণবাত ও প্রতীপ ঘূর্ণবাতের পার্থক্য", "সমুদ্রস্রোত সৃষ্টির কারণসমূহ", "ভরা কোটাল ও মরা কোটালের চিত্রসহ ব্যাখ্যা")
        ),
        Chapter(
            id = "geo_ch3",
            subjectId = "geography",
            chapterNumber = 3,
            titleBn = "ভারত: প্রাকৃতিক পরিবেশ (ভূপ্রকৃতি, নদনদী, জলবায়ু ও মৃত্তিকা)",
            titleEn = "India: Physical Environment",
            marksWeightage = "MCQ: 3 | VSA: 3 | SA: 2 | LA: 5 (Total 16)",
            summary = "হিমালয় পর্বতমালার ভূপ্রকৃতিগত বিভাগ, উত্তর ও দক্ষিণ ভারতের নদীর তুলনা, মৌসুমি জলবায়ুর ওপর জেট বায়ুর প্রভাব এবং কৃষ্ণ মৃত্তিকা ও পলি মৃত্তিকার বৈশিষ্ট্য।",
            keyTopics = listOf("উত্তর ভারতের নদী ও দক্ষিণ ভারতের নদীর পার্থক্য", "ভারতের জলবায়ুতে মৌসুমি বায়ুর প্রভাব", "ল্যাটেরাইট ও কৃষ্ণ মৃত্তিকার অবস্থান ও বৈশিষ্ট্য", "ভারতের স্বাভাবিক উদ্ভিদের শ্রেণীবিভাগ")
        ),
        Chapter(
            id = "geo_ch4",
            subjectId = "geography",
            chapterNumber = 4,
            titleBn = "ভারত: অর্থনৈতিক পরিবেশ (কৃষি, শিল্প ও যোগাযোগ)",
            titleEn = "India: Economic Environment",
            marksWeightage = "MCQ: 3 | VSA: 3 | SA: 2 | LA: 5 + Map: 10 Marks",
            summary = "ধান, গম, চা ও কার্পাস চাষের অনুকূল ভৌগোলিক পরিবেশ, পশ্চিম ভারতে কার্পাস বয়ন শিল্পের একদেশীভবন এবং পূর্ব ভারতে লৌহ-ইস্পাত শিল্পের কেন্দ্রীভবন।",
            keyTopics = listOf("চা চাষের অনুকূল ভৌগোলিক পরিবেশ", "পশ্চিম ভারতে কার্পাস বয়ন শিল্পের কারণ", "পূর্ব ও মধ্য ভারতে লৌহ-ইস্পাত শিল্পের কেন্দ্রীভবন", "ভারতের জনসংখ্যা বণ্টনের তারতম্যের কারণ")
        )
    )

    // Extensive Question Bank covering 1, 2, 3, 5 marks and MCQs with structured exam answers
    val questions: List<Question> = listOf(
        // BENGALI QUESTIONS
        Question(
            id = "q_bn_01",
            subjectId = "bengali",
            chapterId = "bn_ch1",
            chapterTitle = "জ্ঞানচক্ষু",
            questionBn = "তপনের লেখা প্রথম গল্পটির নাম কী ছিল এবং গল্পটি কোন পত্রিকায় ছাপা হয়েছিল?",
            questionEn = "What was the name of Tapan's first story and in which magazine was it published?",
            marks = 1,
            type = QuestionType.VSA,
            difficulty = Difficulty.EASY,
            isVeryImportant = true,
            isOfficialPYQ = true,
            pyqYear = "Madhyamik 2020",
            structuredAnswer = StructuredAnswer(
                directAnswer = "তপনের লেখা প্রথম গল্পটির নাম ছিল 'প্রথম দিন' এবং গল্পটি 'সন্ধ্যাতারা' পত্রিকায় ছাপা হয়েছিল।"
            )
        ),
        Question(
            id = "q_bn_02",
            subjectId = "bengali",
            chapterId = "bn_ch1",
            chapterTitle = "জ্ঞানচক্ষু",
            questionBn = "\"তপনের নতুন মেসোমশাই কোনো সাধারণ মানুষ নন\" — তপনের চোখে ছোটমেসো কেমন ছিলেন?",
            marks = 2,
            type = QuestionType.SA_2,
            difficulty = Difficulty.MEDIUM,
            isVeryImportant = true,
            structuredAnswer = StructuredAnswer(
                directAnswer = "তপন এতদিন ভাবত লেখকরা বুঝি আকাশ থেকে পড়া কোনো অন্য জগতের জীব। কিন্তু ছোটমেসোকে দেখে তপন বুঝল তিনি সাধারণ মানুষদের মতোই দাড়ি কামান, সিগারেট খান, দই ভালোবাসেন এবং খবরের কাগজের খবর নিয়ে তর্ক করেন।"
            )
        ),
        Question(
            id = "q_bn_03",
            subjectId = "bengali",
            chapterId = "bn_ch1",
            chapterTitle = "জ্ঞানচক্ষু",
            questionBn = "\"আজ যেন তার জীবনের সবচেয়ে দুঃখের দিন\" — বক্তা কে? দিনটিকে দুঃখের দিন বলা হয়েছে কেন? তপনের আত্মমর্যাদার স্বরূপ আলোচনা করো।",
            marks = 5,
            type = QuestionType.LA_5,
            difficulty = Difficulty.HARD,
            isVeryImportant = true,
            isOfficialPYQ = true,
            pyqYear = "Madhyamik 2019/2023",
            structuredAnswer = StructuredAnswer(
                directAnswer = "আশাপূর্ণা দেবীর রচিত 'জ্ঞানচক্ষু' ছোটগল্প থেকে উদ্ধৃত অংশে তপনের জীবনের পরম আনন্দের মুহূর্ত কীভাবে আত্মগ্লানির দুঃখের দিনে পরিণত হয়েছিল তা ব্যক্ত হয়েছে।",
                introduction = "উদ্ধৃত অংশে বক্তা গল্পকার আশাপূর্ণা দেবী স্বয়ং এবং তিনি কেন্দ্রীয় চরিত্র বালক তপনের অন্তর্বেদনার কথা উল্লেখ করেছেন।",
                points = listOf(
                    "১. মিথ্যা কৃতিত্বের সংকট: 'সন্ধ্যাতারা' পত্রিকায় প্রকাশিত নিজের 'প্রথম দিন' গল্পটি মা পড়তে বললে তপন পড়তে গিয়ে দেখে ছোটমেসো সংশোধনের নামে সম্পূর্ণ গল্পটি নিজের ভাষায় নতুন করে লিখে দিয়েছেন।",
                    "২. নিজের সৃষ্টি হারানোর বেদনা: পত্রিকায় প্রকাশিত লেখার কোনো লাইনেই তপনের নিজের কোনো অনুভূতির অস্তিত্ব ছিল না।",
                    "৩. আত্মমর্যাদাবোধ ও প্রতিজ্ঞা: তপন সেদিন ছাদে গিয়ে একা চোখের জল ফেলে এবং দৃঢ় প্রতিজ্ঞা করে—ভবিষ্যতে যদি সে কখনো লেখা ছাপতে দেয়, তবে নিজের হাতে দেবে, কারো করুণা বা সংশোধনের জোরে নয়।"
                ),
                explanation = "তপনের যে জ্ঞানচক্ষু প্রথমে একজন লেখককে রক্তমাংসের মানুষরূপে দেখে খুলেছিল, শেষপর্যন্ত আত্মমর্যাদাবোধ এবং স্বকীয় সৃষ্টির মহত্ত্ব অনুধাবন করে তার অন্তরের প্রকৃত জ্ঞানচক্ষু চিরতরে উন্মোচিত হয়।",
                conclusion = "অতএব, আপাত সাফল্যের আড়ালে সৃষ্টিশীলতার অপমৃত্যু ঘটায় তপনের কাছে দিনটি চরম দুঃখের ও বেদনার দিনে পরিণত হয়েছিল।"
            )
        ),
        Question(
            id = "q_bn_mcq_1",
            subjectId = "bengali",
            chapterId = "bn_ch1",
            chapterTitle = "জ্ঞানচক্ষু",
            questionBn = "তপনের মেসোমশাই কোন পত্রিকার সম্পাদককে চিনতেন?",
            marks = 1,
            type = QuestionType.MCQ,
            difficulty = Difficulty.EASY,
            isVeryImportant = true,
            mcqOptions = listOf("শুকতারা", "সন্ধ্যাতারা", "আনন্দমেলা", "দেশ"),
            correctMcqIndex = 1,
            structuredAnswer = StructuredAnswer(
                directAnswer = "সঠিক উত্তর: সন্ধ্যাতারা পত্রিকা।"
            )
        ),

        // MATHEMATICS QUESTIONS
        Question(
            id = "q_m_01",
            subjectId = "math",
            chapterId = "math_ch1",
            chapterTitle = "একচলবিশিষ্ট দ্বিঘাত সমীকরণ",
            questionBn = "ax² + bx + c = 0 (a ≠ 0) সমীকরণের বীজদ্বয় বাস্তব ও সমান হওয়ার শর্তটি লেখো।",
            marks = 1,
            type = QuestionType.VSA,
            difficulty = Difficulty.EASY,
            isVeryImportant = true,
            isOfficialPYQ = true,
            pyqYear = "Madhyamik 2022",
            structuredAnswer = StructuredAnswer(
                directAnswer = "বীজদ্বয় বাস্তব ও সমান হওয়ার শর্ত হলো সমীকরণটির নিরূপক শূন্য হতে হবে, অর্থাৎ: b² - 4ac = 0।"
            )
        ),
        Question(
            id = "q_m_02",
            subjectId = "math",
            chapterId = "math_ch1",
            chapterTitle = "একচলবিশিষ্ট দ্বিঘাত সমীকরণ",
            questionBn = "সমাধান করো: (x - 3) / (x + 3) - (x + 3) / (x - 3) + 6(6/7) = 0 (x ≠ 3, -3)",
            marks = 3,
            type = QuestionType.SA_3,
            difficulty = Difficulty.MEDIUM,
            isVeryImportant = true,
            isOfficialPYQ = true,
            structuredAnswer = StructuredAnswer(
                directAnswer = "x = 4 অথবা x = -9/4",
                introduction = "ধরি, y = (x - 3) / (x + 3)। তাহলে (x + 3) / (x - 3) = 1/y।",
                points = listOf(
                    "পদক্ষেপ ১: প্রদত্ত রাশিমালার রূপান্তর: y - 1/y + 48/7 = 0",
                    "পদক্ষেপ ২: লসাগু করে পাই: 7y² + 48y - 7 = 0",
                    "পদক্ষেপ ৩: মধ্যপদ বিশ্লেষণ: 7y² + 49y - y - 7 = 0 ⇒ (7y - 1)(y + 7) = 0",
                    "পদক্ষেপ ৪: সুতরাং y = 1/7 অথবা y = -7",
                    "পদক্ষেপ ৫: y-এর মান বসিয়ে: (x - 3)/(x + 3) = 1/7 ⇒ 7x - 21 = x + 3 ⇒ 6x = 24 ⇒ x = 4",
                    "পদক্ষেপ ৬: অথবা (x - 3)/(x + 3) = -7 ⇒ x - 3 = -7x - 21 ⇒ 8x = -18 ⇒ x = -9/4"
                ),
                explanation = "শ্রীধর আচার্য বা প্রতিস্থাপন পদ্ধতির মাধ্যমে জটিল ভগ্নাংশকে সহজে সমাধান করা যায়।",
                conclusion = "নির্ণেয় সমাধান: x = 4 এবং x = -2.25।"
            )
        ),
        Question(
            id = "q_m_03",
            subjectId = "math",
            chapterId = "math_ch2",
            chapterTitle = "সরল সুদকষা",
            questionBn = "কোনো মূলধন একই বার্ষিক সরল সুদের হারে ৭ বছরে সুদে-আসলে ৭১০০ টাকা এবং ৪ বছরে সুদে-আসলে ৬২০০ টাকা হলে, মূলধন ও বার্ষিক সুদের হার নির্ণয় করো।",
            marks = 5,
            type = QuestionType.LA_5,
            difficulty = Difficulty.MEDIUM,
            isVeryImportant = true,
            isOfficialPYQ = true,
            pyqYear = "Madhyamik 2023",
            structuredAnswer = StructuredAnswer(
                directAnswer = "মূলধন = ৫০০০ টাকা এবং বার্ষিক সুদের হার = ৬%",
                introduction = "আমরা জানি, সবৃদ্ধিমূল = আসল + মোট সুদ।",
                points = listOf(
                    "১. আসল + ৭ বছরের সুদ = ৭১০০ টাকা",
                    "২. আসল + ৪ বছরের সুদ = ৬২০০ টাকা",
                    "৩. বিয়োগ করে পাই: (৭ - ৪) = ৩ বছরের সুদ = (৭১০০ - ৬২০০) = ৯০০ টাকা",
                    "৪. ১ বছরের সুদ = ৯০০ / ৩ = ৩০০ টাকা",
                    "৫. ৪ বছরের সুদ = ৩০০ × ৪ = ১২০০ টাকা",
                    "৬. নির্ণেয় আসল (P) = সবৃদ্ধিমূল - ৪ বছরের সুদ = ৬২০০ - ১২০০ = ৫০০০ টাকা",
                    "৭. সুদের হার (r): সূত্র I = (P × r × t) / 100",
                    "৮. ১২০০ = (৫০০০ × r × ৪) / ১০০ ⇒ ১২০০ = ২০০r ⇒ r = ৬%"
                ),
                explanation = "যেহেতু সরল সুদে প্রতি বছর সুদের পরিমাণ নির্দিষ্ট থাকে, তাই পার্থক্যের মাধ্যমে সহজেই বাৎসরিক সুদ ও আসল আলাদা করা যায়।",
                conclusion = "উত্তর: মূলধন ৫,০০০ টাকা এবং বার্ষিক সুদের হার ৬%।"
            )
        ),
        Question(
            id = "q_m_mcq_1",
            subjectId = "math",
            chapterId = "math_ch2",
            chapterTitle = "সরল সুদকষা",
            questionBn = "বার্ষিক r% সরল সুদের হারে P টাকার t বছরের সুদ I টাকা হলে নিচের কোনটি সঠিক?",
            marks = 1,
            type = QuestionType.MCQ,
            difficulty = Difficulty.EASY,
            isVeryImportant = true,
            mcqOptions = listOf("I = P · r · t", "I · P · r = 100t", "P · r · t = 100 · I", "P · t = 100 · I · r"),
            correctMcqIndex = 2,
            structuredAnswer = StructuredAnswer(
                directAnswer = "সঠিক উত্তর: P · r · t = 100 · I"
            )
        ),

        // PHYSICAL SCIENCE QUESTIONS
        Question(
            id = "q_phy_01",
            subjectId = "phy_sci",
            chapterId = "phy_ch1",
            chapterTitle = "পরিবেশের জন্য ভাবনা",
            questionBn = "মিথেন হাইড্রেটের সংকেত কী এবং একে 'ফায়ার আইস' বলা হয় কেন?",
            marks = 2,
            type = QuestionType.SA_2,
            difficulty = Difficulty.MEDIUM,
            isVeryImportant = true,
            isOfficialPYQ = true,
            pyqYear = "Madhyamik 2020",
            structuredAnswer = StructuredAnswer(
                directAnswer = "মিথেন হাইড্রেটের সংকেত হলো 4CH₄·23H₂O। এটি সমুদ্রের তলদেশে বা মেরু অঞ্চলের বরফের নিচে কঠিন অবস্থায় থাকে। দেখতে বরফের মতো হলেও এতে প্রচুর পরিমাণে আবদ্ধ মিথেন গ্যাস থাকে, যা আগুন দিলে সহজেই দাহ্য হয়ে পুড়তে থাকে। বরফের মতো দেখতে বস্তুতে আগুন জ্বলার এই অদ্ভুত বৈশিষ্ট্যের কারণে একে 'ফায়ার আইস' (Fire Ice) বলা হয়।"
            )
        ),
        Question(
            id = "q_phy_02",
            subjectId = "phy_sci",
            chapterId = "phy_ch2",
            chapterTitle = "গ্যাসের আচরণ",
            questionBn = "বয়েল ও চার্লসের সূত্রের সমন্বয়ে আদর্শ গ্যাস সমীকরণ (PV = nRT) প্রতিষ্ঠা করো।",
            marks = 3,
            type = QuestionType.SA_3,
            difficulty = Difficulty.MEDIUM,
            isVeryImportant = true,
            isOfficialPYQ = true,
            pyqYear = "Madhyamik 2022",
            structuredAnswer = StructuredAnswer(
                directAnswer = "বয়েলের সূত্রানুসারে V ∝ 1/P এবং চার্লসের সূত্রানুসারে V ∝ T। যৌথ ভেদের নিয়মে PV = KT = nRT প্রমাণিত।",
                introduction = "নির্দিষ্ট ভরের কোনো গ্যাসের চাপ P, আয়তন V এবং পরম তাপমাত্রা T হলে বয়েল ও চার্লসের সূত্র প্রযোজ্য হয়।",
                points = listOf(
                    "১. বয়েলের সূত্র অনুযায়ী (যখন ভর m এবং তাপমাত্রা T স্থির): V ∝ 1/P",
                    "২. চার্লসের সূত্র অনুযায়ী (যখন ভর m এবং চাপ P স্থির): V ∝ T",
                    "৩. অ্যাভোগাড্রো সূত্র অনুযায়ী (চাপ ও তাপমাত্রা স্থির): V ∝ n (মোল সংখ্যা)",
                    "৪. যৌগিক ভেদের উপপাদ্য অনুসারে: V ∝ (n · T) / P",
                    "৫. বা, V = R · (n · T) / P (যেখানে R হলো সার্বজনীন গ্যাস ধ্রুবক)",
                    "৬. সুতরাং, PV = nRT (প্রমাণিত)। ১ মোল গ্যাসের জন্য PV = RT।"
                ),
                explanation = "এই সমীকরণটি গ্যাসের চাপ, আয়তন, তাপমাত্রা এবং পরিমাণের মধ্যে সম্পূর্ণ বাস্তব সম্পর্ক প্রকাশ করে।",
                conclusion = "অতএব বয়েল, চার্লস ও অ্যাভোগাড্রো সূত্রের সমন্বয় রূপই হলো সার্বজনীন আদর্শ গ্যাস সমীকরণ।"
            )
        ),
        Question(
            id = "q_phy_03",
            subjectId = "phy_sci",
            chapterId = "phy_ch4",
            chapterTitle = "চলতড়িৎ",
            questionBn = "তড়িৎপ্রবাহের তাপীয় ফল সংক্রান্ত জুলের সূত্র তিনটি লেখো এবং গাণিতিক রূপ ব্যক্ত করো।",
            marks = 3,
            type = QuestionType.SA_3,
            difficulty = Difficulty.MEDIUM,
            isVeryImportant = true,
            isOfficialPYQ = true,
            structuredAnswer = StructuredAnswer(
                directAnswer = "জুলের তিনটি সূত্র রোধ, প্রবাহমাত্রা ও সময়ের উপর উৎপন্ন তাপের নির্ভরতা ব্যক্ত করে: H ∝ I²Rt।",
                points = listOf(
                    "১. প্রথম সূত্র (প্রবাহমাত্রার সূত্র): পরিবাহীর রোধ (R) এবং প্রবাহকাল (t) স্থির থাকলে পরিবাহীতে উৎপন্ন তাপ পরিবাহী প্রবাহমাত্রার (I) বর্গের সমানুপাতিক, অর্থাৎ H ∝ I²।",
                    "২. দ্বিতীয় সূত্র (রোধের সূত্র): পরিবাহীর প্রবাহমাত্রা (I) এবং প্রবাহকাল (t) স্থির থাকলে উৎপন্ন তাপ রোধের (R) সমানুপাতিক, অর্থাৎ H ∝ R।",
                    "৩. তৃতীয় সূত্র (সময়ের সূত্র): পরিবাহীর প্রবাহমাত্রা (I) এবং রোধ (R) স্থির থাকলে উৎপন্ন তাপ প্রবাহের সময়ের (t) সমানুপাতিক, অর্থাৎ H ∝ t।"
                ),
                conclusion = "একত্রে: H = (I²Rt) / J ক্যালরি (যেখানে J = 4.2 জুল/ক্যালরি হলো যান্ত্রিক সমতা)।"
            )
        ),

        // LIFE SCIENCE QUESTIONS
        Question(
            id = "q_ls_01",
            subjectId = "life_sci",
            chapterId = "life_ch1",
            chapterTitle = "জীবজগতের নিয়ন্ত্রণ ও সমন্বয়",
            questionBn = "হরমোনকে উদ্ভিদের বা প্রাণীর 'রাসায়নিক সমন্বয়ক' (Chemical Coordinator) বলা হয় কেন?",
            marks = 2,
            type = QuestionType.SA_2,
            difficulty = Difficulty.EASY,
            isVeryImportant = true,
            isOfficialPYQ = true,
            structuredAnswer = StructuredAnswer(
                directAnswer = "হরমোন নির্দিষ্ট কোনো অন্তঃক্ষরা অঙ্গ বা কোষে তৈরি হয়ে রক্তের মাধ্যমে সারা দেহে পরিবাহিত হয় এবং দূরবর্তী নির্দিষ্ট লক্ষ্য কোষে (Target organ) পৌঁছে বিপাকীয় ও শারীরবৃত্তীয় কাজের মধ্যে সমন্বয় ঘটায়। কাজ শেষে হরমোন ধ্বংসপ্রাপ্ত হয়। বিভিন্ন অঙ্গের ক্রিয়াকলাপের মধ্যে রাসায়নিক সংকেতের সাহায্যে সামঞ্জস্য রক্ষা করে বলেই একে রাসায়নিক সমন্বয়ক বলা হয়।"
            )
        ),
        Question(
            id = "q_ls_02",
            subjectId = "life_sci",
            chapterId = "life_ch1",
            chapterTitle = "জীবজগতের নিয়ন্ত্রণ ও সমন্বয়",
            questionBn = "একটি আদর্শ নিউরোনের বিজ্ঞানসম্মত চিত্র অঙ্কন করে নিম্নলিখিত অংশগুলি চিহ্নিত করো: অ্যাক্সন, ডেনড্রন, মায়েলিন সিথ, র‍্যানভিয়ারের পর্ব।",
            marks = 5,
            type = QuestionType.LA_5,
            difficulty = Difficulty.MEDIUM,
            isVeryImportant = true,
            isOfficialPYQ = true,
            pyqYear = "Madhyamik 2018/2022/2024",
            structuredAnswer = StructuredAnswer(
                directAnswer = "চিত্র অঙ্কন ৩ নম্বর + ৪টি অংশ চিহ্নিতকরণ ২ নম্বর = মোট ৫ নম্বর।",
                introduction = "নিউরোনের প্রধান দুটি অংশ: কোশদেহ (Soma) এবং প্রবর্ধক (অ্যাক্সন ও ডেনড্রন)।",
                points = listOf(
                    "১. কোশদেহ: নিউক্লিয়াস ও নিসল দানাযুক্ত কেন্দ্রীয় অংশ।",
                    "২. ডেনড্রন: কোশদেহ থেকে নির্গত শাখা-প্রশাখা যা পূর্ববর্তী নিউরোন থেকে সংবেদন গ্রহণ করে।",
                    "৩. অ্যাক্সন: কোশদেহ থেকে উৎপন্ন দীর্ঘ অবিভক্ত প্রবর্ধক যা সংবেদন পরবর্তী নিউরোনে প্রেরণ করে।",
                    "৪. মায়েলিন সিথ: অ্যাক্সনের বাইরের লাইপোপ্রোটিনের আবরণী যা অন্তরণ হিসেবে কাজ করে।",
                    "৫. র‍্যানভিয়ারের পর্ব: মায়েলিন সিথের মধ্যবর্তী ব্যবধান যেখানে স্নায়ু উদ্দীপনা লাফিয়ে প্রবাহিত হয় (Saltatory conduction)।"
                ),
                explanation = "পরীক্ষায় সর্বদা পেন্সিল দিয়ে পরিষ্কার পরিচ্ছন্ন চিত্র আঁকবে এবং ডানপাশে তিরচিহ্ন দিয়ে প্রতিটি অংশ পরিষ্কার হরফে চিহ্নিত করবে।",
                conclusion = "টিপস: অক্ষিগোলক ও নিউরোনের চিত্র প্রতি বছর পর্যায়ক্রমে ৫ নম্বরের নিশ্চিত প্রশ্ন হিসেবে পরীক্ষায় আসে।"
            )
        ),
        Question(
            id = "q_ls_03",
            subjectId = "life_sci",
            chapterId = "life_ch3",
            chapterTitle = "বংশগতি এবং কয়েকটি সাধারণ জিনগত রোগ",
            questionBn = "মেন্ডেলের দ্বিসংকর জনন পরীক্ষাটি চেকারবোর্ডের সাহায্যে ব্যাখ্যা করো এবং ফিনোটাইপ অনুপাত উল্লেখ করো।",
            marks = 5,
            type = QuestionType.LA_5,
            difficulty = Difficulty.HARD,
            isVeryImportant = true,
            isOfficialPYQ = true,
            pyqYear = "Madhyamik 2020/2023",
            structuredAnswer = StructuredAnswer(
                directAnswer = "মেন্ডেলের দ্বিসংকর জননের ফিনোটাইপ অনুপাত হলো ৯ : ৩ : ৩ : ১ এবং জিনোটাইপ অনুপাত ১:২:২:৪:১:২:১:২:১।",
                introduction = "মেন্ডেল দুই জোড়া বিপরীত বৈশিষ্ট্যযুক্ত মটর গাছের মধ্যে সংকরায়ণ ঘটিয়েছিলেন: হলুদ-গোল (YYRR) এবং সবুজ-কুঞ্চিত (yyrr)।",
                points = listOf(
                    "১. P জনু: বিশুদ্ধ হলুদ-গোল (YYRR) × বিশুদ্ধ সবুজ-কুঞ্চিত (yyrr)",
                    "২. F₁ জনু: সকল গাছ সংকর হলুদ-গোল (YyRr) উৎপন্ন হয়।",
                    "৩. F₁ জনুর স্বপরাগযোগ ঘটালে ৪ ধরণের গ্যামেট তৈরি হয়: YR, Yr, yR, yr",
                    "৪. F₂ জনুর চেকারবোর্ড থেকে প্রাপ্ত ১৬টি সম্ভাবনার মধ্যে:",
                    "   • হলুদ ও গোল = ৯টি",
                    "   • হলুদ ও কুঞ্চিত = ৩টি",
                    "   • সবুজ ও গোল = ৩টি",
                    "   • সবুজ ও কুঞ্চিত = ১টি",
                    "৫. প্রাপ্ত সূত্র: স্বাধীন সঞ্চারণ সূত্র (Law of Independent Assortment)।"
                ),
                explanation = "দুই বা ততোধিক জোড়া বিপরীতধর্মী বৈশিষ্ট্যের উপাদানগুলি জনিতৃ থেকে অপত্যে কেবল পৃথকই হয় না, বরং সম্ভাব্য সকল সমন্বয়ে স্বাধীনভাবে বিন্যস্ত হয়।",
                conclusion = "অতএব দ্বিসংকর জনন পরীক্ষাটি প্রমাণ করে যে বিভিন্ন বৈশিষ্ট্যের অ্যালিল পরস্পর স্বাধীনভাবে অপত্য প্রজন্মে সঞ্চারিত হয়।"
            )
        ),

        // HISTORY QUESTIONS
        Question(
            id = "q_h_01",
            subjectId = "history",
            chapterId = "hist_ch2",
            chapterTitle = "সংস্কার: বৈশিষ্ট্য ও পর্যালোচনা",
            questionBn = "নারীশিক্ষা ও বিধবাবিবাহ আন্দোলনে ঈশ্বরচন্দ্র বিদ্যাসাগরের অবদান সংক্ষেপে লেখো।",
            marks = 4,
            type = QuestionType.SA_3,
            difficulty = Difficulty.MEDIUM,
            isVeryImportant = true,
            isOfficialPYQ = true,
            pyqYear = "Madhyamik 2022",
            structuredAnswer = StructuredAnswer(
                directAnswer = "পণ্ডিত ঈশ্বরচন্দ্র বিদ্যাসাগর উনিশ শতকের বাংলায় নারী জাগরণ ও সমাজ সংস্কারের প্রধান কাণ্ডারী ছিলেন।",
                points = listOf(
                    "১. বিধবাবিবাহ আইন প্রণয়ন: পরাশর সংহিতার শ্লোক উদ্ধৃত করে তিনি প্রমাণ করেন বিধবাবিবাহ শাস্ত্রসম্মত। তাঁর অক্লান্ত প্রচেষ্টায় ১৮৫৬ খ্রিস্টাব্দের ২৬ জুলাই লর্ড ক্যানিং-এর আমলে ১৫ নম্বর আইন অনুসারে বিধবাবিবাহ আইনসিদ্ধ হয়।",
                    "২. নিজের জীবনে প্রয়োগ: নিজের একমাত্র পুত্র নারায়ণচন্দ্রকে এক বিধবা নারীর সাথে বিবাহ দিয়ে তিনি চরম সামাজিক সাহসিকতার পরিচয় দেন।",
                    "৩. নারীশিক্ষার প্রসার: তিনি বেথুন সাহেবের সহযোগিতায় বেথুন স্কুল (১৮৪৯) প্রতিষ্ঠা করেন এবং বাংলার বিভিন্ন জেলায় ৩৫টি বালিকা বিদ্যালয় স্থাপন করেন।",
                    "৪. পাঠ্যপুস্তক রচনা: মেয়েদের সহজে মাতৃভাষায় শিক্ষিত করার জন্য 'বর্ণপরিচয়', 'বোধোদয়' প্রভৃতি অমর গ্রন্থ রচনা করেন।"
                ),
                conclusion = "মাইকেল মধুসূদন দত্ত যথার্থই বলেছিলেন বিদ্যাসাগরের ছিল—'সিংহের মতো বিক্রম ও এক বাঙালি মায়ের হৃদয়'।"
            )
        ),

        // GEOGRAPHY QUESTIONS
        Question(
            id = "q_g_01",
            subjectId = "geography",
            chapterId = "geo_ch1",
            chapterTitle = "বহির্জাত প্রক্রিয়া ও তাদের দ্বারা সৃষ্ট ভূমিরূপ",
            questionBn = "নদীর ক্ষয়কার্যের ফলে সৃষ্ট যে-কোনো তিনটি ভূমিরূপের সচিত্র বিবরণ দাও।",
            marks = 5,
            type = QuestionType.LA_5,
            difficulty = Difficulty.MEDIUM,
            isVeryImportant = true,
            isOfficialPYQ = true,
            pyqYear = "Madhyamik 2019/2023",
            structuredAnswer = StructuredAnswer(
                directAnswer = "নদীর উচ্চগতি বা পার্বত্য প্রবাহে প্রবল ক্ষয়কার্যের ফলে সৃষ্ট প্রধান তিনটি ভূমিরূপ হলো: ১. গিরিখাত ও ক্যানিয়ন, ২. জলপ্রপাত, ৩. মন্থকূপ বা পটহোল।",
                introduction = "উচ্চগতিতে নদীর ঢাল বেশি থাকায় জলের বেগ ও নিম্নক্ষয় প্রবল হয়। এর ফলে নিম্নলিখিত বৈশিষ্ট্যপূর্ণ ভূমিরূপ সৃষ্টি হয়:",
                points = listOf(
                    "১. গিরিখাত (Gorge): বৃষ্টিবহুল পার্বত্য অঞ্চলে নদীর প্রবল নিম্নক্ষয়ের ফলে পার্শ্বক্ষয় কম হওয়ায় গভীর ও সংকীর্ণ 'I' ও 'V' আকৃতির গিরিখাত গড়ে ওঠে। যেমন—পেরুর এল ক্যানন দ্য কলকা।",
                    "২. ক্যানিয়ন (Canyon): শুষ্ক মরুপ্রায় অঞ্চলে নদীর কেবল নিম্নক্ষয় চলতে থাকায় অত্যন্ত খাড়া ও গভীর 'I' আকৃতির গিরিখাতকে ক্যানিয়ন বলে। যেমন—যুক্তরাষ্ট্রের কলোরাডো নদীর গ্র্যান্ড ক্যানিয়ন।",
                    "৩. জলপ্রপাত (Waterfall): নদীর প্রবাহপথে কঠিন ও কোমল শিলা উল্লম্বভাবে বা আড়াআড়ি অবস্থান করলে কোমল শিলা দ্রুত ক্ষয় পেয়ে ধাপের সৃষ্টি হয় এবং নদীর জল খাড়াভাবে নিচে আছড়ে পড়ে। যেমন—কর্ণাটকের শরাবতী নদীর যোগ জলপ্রপাত।",
                    "৪. মন্থকূপ (Pot Hole): নদীর তলদেশে ঘূর্ণিজলের সাথে বাহিত নুড়িপাথরের ঘর্ষণে (অবঘর্ষ প্রক্রিয়া) সৃষ্ট ছোট ছোট গোলাকার গর্তকে মন্থকূপ বলে।"
                ),
                explanation = "পরীক্ষার খাতায় তিনটি ভূমিরূপের বিবরণ লেখার সাথে সাথে প্রতিটি ভূমিরূপের স্পষ্ট রেখাচিত্র পেন্সিল দিয়ে অবশ্যই আঁকবে।",
                conclusion = "এই ভূমিরূপগুলি নদীর জলশক্তি ও ভূ-গাঠনিক প্রক্রিয়ার সুন্দর প্রতিফলন ঘটায়।"
            )
        )
    )

    // Complete A-Z Preparation Guide for West Bengal Madhyamik 2027
    val azGuideList: List<AZGuideItem> = listOf(
        AZGuideItem(
            letter = 'A',
            title = "Academic Syllabus Breakdown",
            bengaliTitle = "অ্যাকাডেমিক সিলেবাস বিশ্লেষণ",
            summary = "WBBSE নির্দেশিত প্রতিটি বিষয়ের সম্পূর্ণ নম্বর বিভাজন ও অধ্যায়ভিত্তিক কৌশল।",
            detailedStrategy = "মাধ্যমিক পরীক্ষায় সাফল্য পাওয়ার প্রথম ধাপ হলো সিলেবাসের প্রতিটি অধ্যায়ের নম্বর কাঠামো (Blue Print) নিখুঁতভাবে জানা। ৯১ নম্বরের লিখিত পরীক্ষার মধ্যে গড়ে ৩৬ নম্বর আসে MCQ ও VSA থেকে, যা সরাসরি ফুল মার্কস এনে দেয়। কখনোই কোনো অধ্যায় বাদ দেবে না।",
            actionChecklist = listOf("প্রতিটি পাঠ্যবইয়ের সূচিপত্র প্রিন্ট করে পড়ার টেবিলের সামনে টাঙাও", "কোন অধ্যায়ে ৫ নম্বরের বর্ণনামূলক প্রশ্ন আসবে তা চিহ্নিত করো", "১০ নম্বরের প্রজেক্ট/অভ্যন্তরীণ মূল্যায়নে পূর্ণমান নিশ্চিত করো")
        ),
        AZGuideItem(
            letter = 'B',
            title = "Basic Concepts & Foundation",
            bengaliTitle = "মৌলিক ধারণা ও বেসিক ক্লিয়ারিং",
            summary = "অন্ধের মতো মুখস্থ না করে প্রতিটি বিজ্ঞান ও গণিত তত্ত্বের মূল রহস্য অনুধাবন।",
            detailedStrategy = "মাধ্যমিকের বর্তমান প্রশ্নপত্রে গভীর ধারণামূলক (Conceptual) প্রশ্নের অনুপাত বাড়ছে। ভৌতবিজ্ঞান ও জীবনবিজ্ঞানে যুক্তিহীন মুখস্থবিদ্যা কাজে আসবে না। 'কেন', 'কীভাবে' প্রশ্ন করে প্রতিটি সূত্রের ভিত্তি বোঝো।",
            actionChecklist = listOf("ভৌতবিজ্ঞানের সংজ্ঞাগুলির একক ও মাত্রা খাতায় লিখে রাখো", "জীবনবিজ্ঞানের প্রতিটি অঙ্গের অবস্থান ও কাজ আলাদা করো", "গণিতের উপপাদ্যের স্বীকার্য ও অঙ্কন ভালো করে বোঝো")
        ),
        AZGuideItem(
            letter = 'C',
            title = "Chapter Preparation Routine",
            bengaliTitle = "অধ্যায়ভিত্তিক প্রস্তুতি পদ্ধতি",
            summary = "যেকোনো নতুন অধ্যায় আয়ত্ত করার ৪ ধাপের বৈজ্ঞানিক পদ্ধতি।",
            detailedStrategy = "অধ্যায় প্রস্তুতির সোনালী নিয়ম: পাঠ্যবইয়ের রিডিং ২ বার → গুরুত্বপূর্ণ লাইনের নিচে দাগ → সংক্ষেপিত নোট তৈরি → বিগত ৫ বছরের প্রশ্ন সমাধান। এক অধ্যায় শেষ না করে অন্য অধ্যায়ে ঝাঁপিয়ে পড়বে না।",
            actionChecklist = listOf("প্রথম পাঠ: কেবল বোঝার জন্য রিডিং", "দ্বিতীয় পাঠ: পেনসিল দিয়ে গুরুত্বপূর্ণ তথ্যের নিচে আন্ডারলাইন", "অনুশীলনীর সমস্ত প্রশ্ন না দেখে নিজে উত্তর লেখার অভ্যাস")
        ),
        AZGuideItem(
            letter = 'D',
            title = "Daily Study Plan Discipline",
            bengaliTitle = "দৈনিক অধ্যয়ন পরিকল্পনা",
            summary = "সকাল, দুপুর, বিকেল ও রাতের বৈজ্ঞানিক সময় বণ্টন।",
            detailedStrategy = "সকালে যখন মস্তিষ্ক তরতাজা থাকে তখন গণিত বা ব্যাকরণ করো। স্কুল থেকে ফিরে বিশ্রাম নিয়ে ইতিহাস বা ভূগোল রিডিং পড়ো। সন্ধ্যায় বিজ্ঞান এবং রাতে শোবার আগে দিনভর পড়া জিনিসের ১৫ মিনিটের কুইক রিভিশন।",
            actionChecklist = listOf("প্রতিদিন ন্যূনতম ৩.৫ থেকে ৪ ঘণ্টা সেলফ স্টাডি", "একটানা না পড়ে ৪৫ মিনিট পড়ার পর ৫ মিনিটের বিরতি", "দৈনিক টার্গেট ডায়েরিতে লিখে টিক চিহ্ন দাও")
        ),
        AZGuideItem(
            letter = 'E',
            title = "Exam Strategy & Time Management",
            bengaliTitle = "পরীক্ষার রণকৌশল ও সময় ব্যবস্থাপনা",
            summary = "৩ ঘণ্টা ১৫ মিনিটের সর্বোচ্চ ব্যবহারের মাস্টারপ্ল্যান।",
            detailedStrategy = "প্রথম ১৫ মিনিট শুধু প্রশ্নপত্র ভালো করে পড়ার জন্য। প্রথমে সমস্ত MCQ ও VSA ৩০-৪০ মিনিটের মধ্যে শেষ করো। এরপর ৩ ও ৫ নম্বরের বড় প্রশ্ন ঠান্ডা মাথায় সাজিয়ে লেখো। শেষ ১৫ মিনিট খাতা রিভিশন ও দাগ নম্বর মেলানোর জন্য সংরক্ষিত রাখো।",
            actionChecklist = listOf("প্রশ্নপত্র পাওয়ার সাথে সাথে বিভাগ 'ক' (MCQ) দাগাও", "প্রতিটি উত্তরের মাঝে দুই আঙুল ফাঁক রাখো", "খাতার চারদিকে ১ ইঞ্চি মার্জিন পেন্সিল দিয়ে টানো")
        ),
        AZGuideItem(
            letter = 'F',
            title = "Frequently Asked Questions (FAQ)",
            bengaliTitle = "বারবার আসা প্রশ্ন ও উত্তর কাঠামো",
            summary = "বোর্ডের বিগত ৮ বছরে অন্তত ৩ বার আসা গোল্ডেন প্রশ্নমালা।",
            detailedStrategy = "কিছু প্রশ্ন বোর্ডের পরীক্ষার জন্য চিরাচরিত অমূল্য রত্ন—যেমন অক্ষিগোলকের চিহ্নিত চিত্র, নদীর ক্ষয়কার্যের তিনটি ভূমিরূপ, চার্লস-বয়েলের সমন্বয় সূত্র, উপপাদ্য ৩২ ও ৩৪। এই প্রশ্নগুলির উত্তর লিখে লিখে শতভাগ ত্রুটিহীন করো।",
            actionChecklist = listOf("বোর্ডের ফেভারিট ২০টি ৫-নম্বরি প্রশ্নের মাস্টার খাতা তৈরি", "পয়েন্ট করে উত্তর লেখার ছক আয়ত্ত করা", "উত্তরের সাথে প্রাসঙ্গিক উদাহরণের সংযোজন")
        ),
        AZGuideItem(
            letter = 'G',
            title = "Grammar & Terminology Mastery",
            bengaliTitle = "ব্যাকরণ ও বৈজ্ঞানিক পরিভাষা",
            summary = "বাংলা, ইংরেজি ব্যাকরণ ও বিজ্ঞানের সঠিক পরিভাষা লেখার নিয়ম।",
            detailedStrategy = "বাংলা ব্যাকরণে কারক ও সমাস প্রতিদিন ৫টি করে ব্যাসবাক্য অভ্যাস করো। ইংরেজিতে Voice, Narration ও Phrasal verbs প্রতিদিন প্র্যাকটিস করো। বিজ্ঞানে 'জারণ', 'বিজারণ', 'অ্যানাফেজ', 'অভিসারী বিবর্তন' এর মতো সঠিক শব্দ ছাড়া পুরো নম্বর পাওয়া যায় না।",
            actionChecklist = listOf("প্রতিদিন ৫টি করে ইংরেজি Phrasal Verb মুখস্থ করো", "সমাসের নিয়মাবলী চার্ট আকারে চোখের সামনে রাখো", "ভুল বানান নিয়মিত সংশোধন করো")
        ),
        AZGuideItem(
            letter = 'H',
            title = "High-Priority Questions (VVI)",
            bengaliTitle = "সর্বোচ্চ অগ্রাধিকারের প্রশ্ন তালিকা",
            summary = "যে প্রশ্নগুলি ২০২৭ মাধ্যমিকে আসার সম্ভাবনা ৯৫% এর বেশি।",
            detailedStrategy = "বোর্ডের প্রশ্নপত্রের ট্রেন্ড বিশ্লেষণ করে চিহ্নিত করা VVI প্রশ্নগুলির বিশেষ তালিকা। এই প্রশ্নগুলির উত্তর একাধিকবার লিখে সময় মেপে প্র্যাকটিস করো যাতে পরীক্ষায় হাত না থামে।",
            actionChecklist = listOf("VVI প্রশ্নগুলিকে খাতায় লাল কালি দিয়ে চিহ্নিত করো", "প্রতি রবিবারে ২টি করে VVI প্রশ্নের পূর্ণাঙ্গ মক টেস্ট", "শিক্ষকের কাছ থেকে খাতা যাচাই করে নাও")
        ),
        AZGuideItem(
            letter = 'I',
            title = "Important Formula Sheet",
            bengaliTitle = "গুরুত্বপূর্ণ সূত্রের মাস্টার শিট",
            summary = "গণিত ও ভৌতবিজ্ঞানের সমস্ত সূত্রের এক ঝলক রিভিশন ব্যাংক।",
            detailedStrategy = "পাটিগণিত (I = Prt/100, A = P(1+r/100)ⁿ), পরিমিতি (চোঙ, শঙ্কু, গোলক, আয়তঘন), দ্বিঘাত করণী ও চলতড়িতের সূত্রের জন্য একটি ছোট পকেট নোটবুক তৈরি করো। প্রতিদিন ঘুমানোর আগে একবার চোখ বোলাও।",
            actionChecklist = listOf("সমস্ত পরিমিতির সূত্র একক সহ মুখস্থ করো", "বিজ্ঞানের মাত্রা ও এসআই একক আলাদা পাতায় লেখো", "শ্রীধর আচার্যের সূত্রের প্রয়োগ প্রতিদিন একটি অংকে করো")
        ),
        AZGuideItem(
            letter = 'J',
            title = "Job-Style Time Discipline",
            bengaliTitle = "কঠোর সময়ানুবর্তিতা ও পোমোডোরো টেকনিক",
            summary = "পড়ার সময় মোবাইল ফোনের বিভ্রান্তি দূর করে গভীর মনোযোগ।",
            detailedStrategy = "পড়ার সময় সমস্ত সোশ্যাল মিডিয়া ও নোটিফিকেশন বন্ধ রাখো। ২৫ মিনিট গভীর পড়াশোনা এবং ৫ মিনিট হালকা বিশ্রাম (Pomodoro technique)। এটি পড়ার গতি ও স্মরণশক্তি দ্বিগুণ করে দেয়।",
            actionChecklist = listOf("পড়ার টেবিলে শুধু যে বই পড়ছ সেটাই রাখো", "ঘড়ি দেখে নির্দিষ্ট সময়ে টেবিলে বসা", "দিনের পড়া শেষ না করে না ওঠার মানসিক সংকল্প")
        ),
        AZGuideItem(
            letter = 'K',
            title = "Key Points Highlighting",
            bengaliTitle = "কী-পয়েন্ট ও আন্ডারলাইন কৌশল",
            summary = "পরীক্ষকের চোখ কাড়ার জন্য উত্তরের মূল শব্দের হাইলাইটিং।",
            detailedStrategy = "উত্তরে পরীক্ষক পুরো প্যারাগ্রাফ খুঁটিয়ে পড়ার সময় পান না। তিনি খোঁজেন 'Key words'। তাই কালো বা নীল কালির উত্তরের মাঝে মুখ্য শব্দ বা সন-তারিখের নিচে পেনসিল দিয়ে আন্ডারলাইন করো।",
            actionChecklist = listOf("উত্তরের প্রধান সাল ও ব্যক্তির নাম আন্ডারলাইন", "বিজ্ঞানের প্রধান সূত্র বক্স করে দেখানো", "উত্তরে অপ্রয়োজনীয় ভূমিকা না বাড়িয়ে সরাসরি তথ্যে যাওয়া")
        ),
        AZGuideItem(
            letter = 'L',
            title = "Last-Minute Revision Checklist",
            bengaliTitle = "পরীক্ষার আগের রাতের রিভিশন চেকলিস্ট",
            summary = "শেষ মুহূর্তে কী পড়বে আর কী কখনোই নতুন করে শুরু করবে না।",
            detailedStrategy = "পরীক্ষার আগের রাতে কখনোই কোনো অচেনা বা নতুন টপিক শুরু করবে না। এতদিন ধরে তৈরি করা সংক্ষিপ্ত নোট, ফর্মুলা শিট ও ভুল হওয়ার খাতাটি (Mistake notebook) উল্টে দেখো। পর্যাপ্ত ৭ ঘণ্টার ঘুম নিশ্চিত করো।",
            actionChecklist = listOf("অ্যাডমিট কার্ড, রেজিস্ট্রেশন ও জ্যামিতি বক্স গুছিয়ে রাখা", "সংক্ষিপ্ত ফর্মুলা শিটে চোখ বোলানো", "রাত ১১টার মধ্যে ঘুমিয়ে পড়া")
        ),
        AZGuideItem(
            letter = 'M',
            title = "Mock Test Series Strategy",
            bengaliTitle = "মক টেস্ট সিরিজ ও সময়ানুবর্তিতা",
            summary = "ঘড়ি ধরে পুরো ৯০ নম্বরের পরীক্ষার অনুকরণ।",
            detailedStrategy = "বছরে অন্তত ১৫টি পূর্ণাঙ্গ ৯০ নম্বরের মক টেস্ট দেওয়া বাধ্যতামূলক। ঘড়িতে ঠিক ৩ ঘণ্টা ১৫ মিনিট সময় ধরে টেবিলে বসে পরীক্ষা দাও। এটি পরীক্ষার ভীতি, নার্ভাসনেস ও সময় শেষ হওয়ার সমস্যা সমূলে দূর করে।",
            actionChecklist = listOf("প্রতি মাসে অন্তত ২টি সম্পূর্ণ বিষয়ের মক টেস্ট", "পরীক্ষা শেষে নম্বর হিসাব করে দুর্বল অধ্যায় চিহ্নিত করা", "ভুল হওয়া প্রশ্নগুলি সাথে সাথে সমাধান করা")
        ),
        AZGuideItem(
            letter = 'N',
            title = "Notes & Clean Presentation",
            bengaliTitle = "সুন্দর হস্তাক্ষর ও খাতা সাজানো",
            summary = "পরিচ্ছন্ন উপস্থাপনা অতিরিক্ত ৫-৮ নম্বর নিশ্চিত করে।",
            detailedStrategy = "মাধ্যমিকের খাতা যারা দেখেন তাদের প্রথম আকর্ষণ পরিচ্ছন্নতা। প্রতি উত্তরের মাঝে স্পষ্ট ফাঁক, পরিষ্কার কাটাকুটি (একটি মাত্র সোজা দাগ টেনে কাটা) এবং মার্জিন টেনে সুন্দরভাবে উপস্থাপন করো।",
            actionChecklist = listOf("কাটাকুটি হলে ঘষে নোংরা না করে একটি সোজা লাইন কাটো", "পয়েন্টগুলি বুলেটে বা নম্বরে লেখো", "গাঢ় নীল বা কালো বলপয়েন্ট পেন ব্যবহার করো")
        ),
        AZGuideItem(
            letter = 'O',
            title = "Objective Questions 100% Score",
            bengaliTitle = "অবজেক্টিভ প্রশ্নে ৩৬-এ ৩৬ পাওয়ার কৌশল",
            summary = "MCQ ও VSA-তে এক নম্বরও নষ্ট না করার গোপন চাবিকাঠি।",
            detailedStrategy = "লিখিত ৯০ নম্বরের মধ্যে প্রায় ৩৬ নম্বর থাকে ছোট প্রশ্ন। পাঠ্যবইয়ের খুঁটিনাটি লাইন যারা রিডিং পড়ে তারা অনায়াসে এই ৩৬-এ ৩৬ তুলে নেয়। এতে প্রথম বিভাগেই ফার্স্ট ডিভিশনের পথ প্রশস্ত হয়।",
            actionChecklist = listOf("প্রতিটি অধ্যায়ের শেষে থাকা কুইক টেবিল মুখস্থ করা", "বিকল্প উত্তরের ক্ষেত্রে চারটে অপশনই সাবধানে পড়া", "সংশয় থাকলে দাগ নম্বর দিয়ে পরে রিচেক করা")
        ),
        AZGuideItem(
            letter = 'P',
            title = "Previous Year Questions (PYQ) Mastery",
            bengaliTitle = "বিগত ১০ বছরের প্রশ্নপত্র মন্থন",
            summary = "২০১৭ থেকে ২০২৪ সালের প্রশ্ন বিশ্লেষণ ও প্যাটার্ন ধরা।",
            detailedStrategy = "বোর্ডের প্রশ্ন কখনোই মঙ্গলগ্রহ থেকে আসে না। বিগত বছরের প্রশ্নের ধাঁচেই ৬০-৭০% প্রশ্ন সামান্য ঘুরিয়ে আসে। PYQ সলভ করলে তুমি বুঝতে পারবে বোর্ড ঠিক কী ধরনের ভাষা পছন্দ করে।",
            actionChecklist = listOf("বিগত ৫ বছরের মাধ্যমিক প্রশ্নপত্র সংগ্রহ করো", "যে প্রশ্নগুলি বারবার এসেছে সেগুলি তারকাচিহ্নিত করো", "টেস্ট পেপার বেরোলে অন্তত ২০টি স্কুলের সেট সমাধান করো")
        ),
        AZGuideItem(
            letter = 'Q',
            title = "Question Bank Categorization",
            bengaliTitle = "১, ২, ৩ ও ৫ নম্বরের প্রশ্ন বিন্যাস",
            summary = "নম্বর অনুযায়ী উত্তরের আকার নির্ধারণ।",
            detailedStrategy = "১ নম্বরের উত্তর হবে ১-২ শব্দ বা এক লাইনে। ২ নম্বরের উত্তরে মূল সংজ্ঞা বা দুটি স্পষ্ট পার্থক্য। ৩ নম্বরে স্পষ্ট তিনটি পয়েন্ট। ৫ নম্বরে ভূমিকা, ৩-৪টি বিশদ পয়েন্ট ও উপসংহার। অপ্রয়োজনীয় লম্বা করে সময় নষ্ট করবে না।",
            actionChecklist = listOf("১ নম্বরের প্রশ্নে ভূমিকা লেখা বর্জন করো", "পার্থক্যের প্রশ্নে অবশ্যই 'বিষয়' বা 'ভিত্তি'র কলাম রাখো", "৫ নম্বরের প্রশ্নে বৈজ্ঞানিক চিত্র বা সমীকরণ যুক্ত করো")
        ),
        AZGuideItem(
            letter = 'R',
            title = "Revision: Spaced Repetition",
            bengaliTitle = "স্পেসড রিপিটেশন রিভিশন পদ্ধতি",
            summary = "১ দিন, ৩ দিন, ৭ দিন ও ৩০ দিনের পর রিভিশনের বিজ্ঞান।",
            detailedStrategy = "মনোবিজ্ঞানের 'ফরগেটিং কার্ভ' বলে যেকোনো নতুন পড়া ৪৮ ঘণ্টার মধ্যে ৮০% ভুলে যাই যদি রিভিশন না করি। তাই আজ যা পড়বে, কাল সকালে ১০ মিনিট, ৩ দিন বাদে একবার এবং রবিবার আরেকবার ঝালিয়ে নাও। চিরতরে মনে থাকবে।",
            actionChecklist = listOf("স্টাডিমেটের 'Smart Revision' অ্যালার্ম ব্যবহার করো", "রিভিশন শেষে আত্মবিশ্বাসের স্তর রেকর্ড করো", "কঠিন তথ্য বন্ধুদের সাথে আলোচনা করো")
        ),
        AZGuideItem(
            letter = 'S',
            title = "Suggestions for Madhyamik 2027",
            bengaliTitle = "২০২৭ মাধ্যমিকের জন্য বিশেষ সাজেশন",
            summary = "বিশেষজ্ঞ শিক্ষকদের নির্বাচিত সবচেয়ে সম্ভাবনাময় অধ্যায়।",
            detailedStrategy = "২০২৭ সালের মাধ্যমিকের জন্য প্রতিটি বিষয়ের সম্ভাব্য বিশেষ অধ্যায়গুলি অগ্রাধিকার ভিত্তিতে সম্পূর্ণ প্রস্তুত রাখো। তবে মনে রাখবে, শর্ট প্রশ্নের কোনো সাজেশন হয় না—তার জন্য পাঠ্যবই খুটিয়ে পড়া অপরিহার্য।",
            actionChecklist = listOf("ইংরেজি রাইটিং স্কিলে লেটার, নোটিস ও রিপোর্ট চর্চা", "ভূগোলের ভারতের অর্থনৈতিক পরিবেশ ও ৫টি মানচিত্র পয়েন্টিং", "ইতিহাসের মহাবিদ্রোহ ও আধুনিক শিক্ষা সংস্কার")
        ),
        AZGuideItem(
            letter = 'T',
            title = "Test Series & Weakness Rectification",
            bengaliTitle = "টেস্ট সিরিজ ও দুর্বলতা মেরামত",
            summary = "যেখানে ভুল হচ্ছে তাকে খুঁজে বের করে শক্তিশালী করা।",
            detailedStrategy = "পরীক্ষায় কম নম্বর পাওয়া খারাপ নয়, কিন্তু ভুল থেকে শিক্ষা না নেওয়া অপরাধ। মক টেস্টে যেসব অংক বা প্রশ্নের উত্তর ভুল হয়েছে তার জন্য একটি আলাদা 'ভুলের খাতা' (Mistake Book) তৈরি করো।",
            actionChecklist = listOf("প্রতিটি ভুল উত্তরের সঠিক সমাধান লিখে রাখা", "পরের পরীক্ষায় সেই একই ভুল দ্বিতীয়বার না করা", "দুর্বল অধ্যায়ে দ্বিগুণ সময় বরাদ্দ করা")
        ),
        AZGuideItem(
            letter = 'U',
            title = "Understanding Weak Topics",
            bengaliTitle = "দুর্বল বিষয়ের ভয় জয় করার কৌশল",
            summary = "গণিত বা ভৌতবিজ্ঞানের ভয় দূর করে ভালোবাসায় রূপান্তর।",
            detailedStrategy = "যেই বিষয়ে ভয় লাগে তাকে এড়িয়ে চলবে না। প্রতিদিনের পড়া শুরুই করো সেই ভয়ের বিষয় দিয়ে। ছোট ছোট সহজ টপিক দিয়ে শুরু করো, ধীরে ধীরে আত্মবিশ্বাস ও আগ্রহ বহুগুণ বেড়ে যাবে।",
            actionChecklist = listOf("AI টিচারের কাছে সহজ ভাষায় ব্যাখ্যা চাও", "প্রতিদিন অন্তত ১ ঘণ্টা দুর্বল বিষয়ে সময় দাও", "সহজ উদাহরণ দিয়ে কঠিন ধারণার সূচনা করো")
        ),
        AZGuideItem(
            letter = 'V',
            title = "Very Important Diagrams & Maps",
            bengaliTitle = "জীবনবিজ্ঞান চিত্র ও ভূগোল মানচিত্র",
            summary = "চিত্র ও মানচিত্রে নিশ্চিত ১৫ নম্বর পকেটে পোরা।",
            detailedStrategy = "জীবনবিজ্ঞানে ৫ নম্বরের চিত্র প্রতি বছর আসে। ভূগোল মানচিত্রে ১০ এ ১০ পাওয়া সবচেয়ে সহজ। মানচিত্রের গুরুত্বপূর্ণ পাহাড়, নদী, মৃত্তিকা অঞ্চল ও শিল্পাঞ্চল প্রতি সপ্তাহে ২ বার করে ড্র করো।",
            actionChecklist = listOf("ভারতের ব্ল্যাঙ্ক ম্যাপে ১০টি স্থান পয়েন্টিং প্র্যাকটিস", "চোখ, নিউরোন ও ক্রোমোজোমের চিত্র ৫ বার এঁকে নিখুঁত করো", "চিত্রের লেবেলিং কেবল ডানপাশে ক্যাপিটাল লেটারে করার চেষ্টা")
        ),
        AZGuideItem(
            letter = 'W',
            title = "Weekly Cumulative Revision",
            bengaliTitle = "সাপ্তাহিক মহা-রিভিশন",
            summary = "রবিবার কোনো নতুন পড়া নয়—শুধু পুরোনো পড়ার সংরক্ষণ।",
            detailedStrategy = "সপ্তাহের ছয় দিন যা পড়েছ, রবিবারের দিনটি তার পূর্ণাঙ্গ রিভিশনের জন্য উৎসর্গ করো। নতুন পড়ার চেয়ে পুরোনো পড়া মাথায় ধরে রাখা অনেক বেশি গুরুত্বপূর্ণ।",
            actionChecklist = listOf("সপ্তাহের সমস্ত নোট ও ফর্মুলা একবার পুরো পড়া", "একটি ৫০ নম্বরের উইকলি মক টেস্ট সমাধান", "পরবর্তী সপ্তাহের স্টাডি প্ল্যান তৈরি করা")
        ),
        AZGuideItem(
            letter = 'X',
            title = "Extra Practice & Numericals",
            bengaliTitle = "গাণিতিক উদাহরণ ও অতিরিক্ত অনুশীলন",
            summary = "ভৌতবিজ্ঞান ও গণিতের নিউমেরিক্যালসে ফুল মার্কস।",
            detailedStrategy = "ভৌতবিজ্ঞানে গ্যাসের সূত্র (PV=nRT), আলো (লেন্স ও প্রতিসরাঙ্ক) এবং চলতড়িতের (রোধ, তাপীয় ফল, বিদ্যুৎ খরচ) অংক প্রতি বছর আসে। এই অংকগুলি ফর্মুলা লিখে স্টেপ-বাই-স্টেপ সমাধান করো।",
            actionChecklist = listOf("অংকের উত্তরের শেষে একক (Unit) লিখতে কখনো ভুলবে না", "রাফ খাতার ডানপাশে স্পষ্ট করে দাগ টেনে করবে", "প্রদত্ত মানগুলি আগে আলাদা করে লিখে নেবে")
        ),
        AZGuideItem(
            letter = 'Y',
            title = "Year-End Revision Blueprint",
            bengaliTitle = "বর্ষশেষের চূড়ান্ত রিভিশন ব্লুপ্রিন্ট",
            summary = "পরীক্ষার শেষ ২ মাসের নিশ্ছিদ্র প্রস্তুতি ছক।",
            detailedStrategy = "নভেম্বর ও ডিসেম্বরের মধ্যে সম্পূর্ণ সিলেবাস প্রথম রাউন্ড শেষ করে টেস্ট পেপার ধরা। জানুয়ারি মাস পুরোপুরি রিভিশন, স্পিড টেস্ট এবং দুর্বল টপিক মেরামতের জন্য উৎসর্গ করা।",
            actionChecklist = listOf("ডিসেম্বরের মধ্যে সিলেবাস শতভাগ শেষ করা", "টেস্ট পরীক্ষার ফলাফল বিশ্লেষণ করে পরিকল্পনা সাজানো", "শারীরিক স্বাস্থ্যের যত্ন নেওয়া ও সুষম খাদ্য গ্রহণ")
        ),
        AZGuideItem(
            letter = 'Z',
            title = "Zero-Backlog Strategy",
            bengaliTitle = "জিরো-ব্যাকলগ নীতি",
            summary = "কোনো পড়া আগামীকালের জন্য জমিয়ে না রাখার বজ্রকঠিন সংকল্প।",
            detailedStrategy = "পড়া জমে গেলে তা পাহাড়ের মতো মানসিক চাপ তৈরি করে। কোনো কারণে যদি আজ ১টি সেশন মিস হয়, তবে স্টাডিমেট অ্যাপের 'Catch-up' সিস্টেম ব্যবহার করে পরবর্তী ২ দিনের মধ্যে তা সমানভাবে ভাগ করে নাও। পড়া জমতে দিও না।",
            actionChecklist = listOf("আজকের টার্গেট আজকেই শেষ করো", "মিস হওয়া সেশন ড্যাশবোর্ডে ব্যাকলগ হিসেবে দেখতে পেলে সাথে সাথে শিডিউল করো", "\"কাল করব\" এই ভাবনাকে চিরতরে বিদায় দাও")
        )
    )

    // Flashcards for quick active recall
    val flashcards: List<Flashcard> = listOf(
        Flashcard("fc_1", "math", "সরল সুদকষা", "সরল সুদের সূত্র (Simple Interest Formula)", "I = (P × r × t) / 100\nযেখানে P = আসল, r = বার্ষিক সুদের হার, t = বছর, I = মোট সুদ।", "Formula"),
        Flashcard("fc_2", "math", "আয়তঘন", "আয়তঘনের কর্ণের দৈর্ঘ্য (Diagonal of Cuboid)", "দৈর্ঘ্য = √(l² + b² + h²)\nযেখানে l = দৈর্ঘ্য, b = প্রস্থ, h = উচ্চতা।", "Formula"),
        Flashcard("fc_3", "phy_sci", "গ্যাসের আচরণ", "পরম শূন্য তাপমাত্রা (Absolute Zero Temperature)", "-273.15°C অথবা 0 Kelvin। এই তাপমাত্রায় যেকোনো গ্যাসের আয়তন ও চাপ তাত্ত্বিকভাবে শূন্য হয়।", "Definition"),
        Flashcard("fc_4", "phy_sci", "চলতড়িৎ", "B.O.T (Board of Trade Unit) কী?", "১ B.O.T = ১ কিলোওয়াট-ঘণ্টা (kWh) = 3.6 × 10⁶ জুল। এটি বাণিজ্যিক বিদ্যুৎ পরিমাপের একক।", "Definition"),
        Flashcard("fc_5", "life_sci", "জীবনের প্রবাহমানতা", "মাইটোসিসের কোন দশায় ক্রোমোজোম স্পষ্ট দৃশ্যমান হয়?", "মেটাফেজ দশা (Metaphase)। এই দশায় ক্রোমোজোমগুলি বিষুব অঞ্চলে বিন্যস্ত হয়।", "Concept"),
        Flashcard("fc_6", "life_sci", "বংশগতি", "মেন্ডেলের একসংকর জননের ফিনোটাইপ অনুপাত", "ফিনোটাইপ অনুপাত ৩ : ১ (লম্বা : বেঁটে)\nজিনোটাইপ অনুপাত ১ : ২ : ১ (বিশুদ্ধ লম্বা : সংকর লম্বা : বিশুদ্ধ বেঁটে)", "Concept"),
        Flashcard("fc_7", "history", "সংস্কার", "বিধবাবিবাহ আইন কবে পাস হয়?", "১৮৫৬ খ্রিস্টাব্দের ২৬ জুলাই, ঈশ্বরচন্দ্র বিদ্যাসাগরের উদ্যোগে ও লর্ড ক্যানিং-এর শাসনকালে (আইন নং ১৫)।", "Year"),
        Flashcard("fc_8", "history", "সংঘবদ্ধতা", "আনন্দমঠ উপন্যাস কার রচনা এবং কোন সংগীত এতে আছে?", "বঙ্কিমচন্দ্র চট্টোপাধ্যায়ের রচিত এবং এতে ভারতের জাতীয় স্তোত্র 'বন্দে মাতরম্' রয়েছে (১৮৮২)।", "Fact"),
        Flashcard("fc_9", "geography", "বহির্জাত প্রক্রিয়া", "পৃথিবীর বৃহত্তম ব-দ্বীপ কোনটি?", "গঙ্গা-ব্রহ্মপুত্র-মেঘনা ব-দ্বীপ (সুন্দরবন অঞ্চল)।", "Fact"),
        Flashcard("fc_10", "geography", "ভারত", "ভারতের বৃহত্তম তথ্যপ্রযুক্তি শিল্পকেন্দ্র কোনটি?", "বেঙ্গালুরু (কর্নাটক) — একে ভারতের 'সিলিকন ভ্যালি' বলা হয়।", "Fact")
    )
}
