-- SEQUENCES
CREATE SEQUENCE IF NOT EXISTS students_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS teachers_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS enrollments_seq START WITH 1 INCREMENT BY 1;

-- SUBJECTS
CREATE TABLE IF NOT EXISTS subjects (
    id           VARCHAR(255) PRIMARY KEY,
    name         VARCHAR(255) NOT NULL UNIQUE,
    school       VARCHAR(100) NOT NULL,
    credits      INTEGER      NOT NULL,
    description  TEXT,
    max_students INTEGER      NOT NULL,
    is_active    BOOLEAN      NOT NULL,
    created_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT subjects_school_chk CHECK (school IN (
        'SCHOOL_OF_COMPUTING_AND_INFORMATICS',
        'SCHOOL_OF_BUSINESS_AND_MANAGEMENT',
        'SCHOOL_OF_ENGINEERING_AND_TECHNOLOGY',
        'SCHOOL_OF_HUMANITIES_AND_SOCIAL_SCIENCES',
        'SCHOOL_OF_LAW_AND_POLITICAL_SCIENCE'
    ))
);

-- ELEMENT COLLECTION: prerequisites
CREATE TABLE IF NOT EXISTS subject_prerequisites (
    subject_id   VARCHAR(255) NOT NULL,
    prerequisite VARCHAR(255) NOT NULL,
    PRIMARY KEY (subject_id, prerequisite),
    CONSTRAINT fk_subject_prereq_subject
        FOREIGN KEY (subject_id) REFERENCES subjects(id) ON DELETE CASCADE
);

-- STUDENTS
CREATE TABLE IF NOT EXISTS students (
    id            BIGINT       PRIMARY KEY DEFAULT nextval('students_seq'),
    email         VARCHAR(255) NOT NULL UNIQUE,
    first_name    VARCHAR(255) NOT NULL,
    last_name     VARCHAR(255) NOT NULL,
    date_of_birth DATE         NOT NULL,
    gender        VARCHAR(10)  NOT NULL,
    phone_number  VARCHAR(50)  NOT NULL,
    school        VARCHAR(100) NOT NULL,
    year_of_study INTEGER      NOT NULL,
    gpa           REAL                  DEFAULT 0.0,
    is_active     BOOLEAN      NOT NULL,
    created_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT students_gender_chk CHECK (gender IN ('MALE', 'FEMALE')),
    CONSTRAINT students_school_chk CHECK (school IN (
        'SCHOOL_OF_COMPUTING_AND_INFORMATICS',
        'SCHOOL_OF_BUSINESS_AND_MANAGEMENT',
        'SCHOOL_OF_ENGINEERING_AND_TECHNOLOGY',
        'SCHOOL_OF_HUMANITIES_AND_SOCIAL_SCIENCES',
        'SCHOOL_OF_LAW_AND_POLITICAL_SCIENCE'
    ))
);

-- TEACHERS
CREATE TABLE IF NOT EXISTS teachers (
    id           BIGINT       PRIMARY KEY DEFAULT nextval('teachers_seq'),
    email        VARCHAR(255) NOT NULL UNIQUE,
    first_name   VARCHAR(255) NOT NULL,
    last_name    VARCHAR(255) NOT NULL,
    gender       VARCHAR(10)  NOT NULL,
    phone_number VARCHAR(50)  NOT NULL,
    is_active    BOOLEAN      NOT NULL,
    created_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT teachers_gender_chk CHECK (gender IN ('MALE', 'FEMALE'))
);

-- MANY-TO-MANY: teacher_subjects
CREATE TABLE IF NOT EXISTS teacher_subjects (
    teacher_id BIGINT       NOT NULL,
    subject_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (teacher_id, subject_id),
    CONSTRAINT fk_teacher_subjects_teacher
        FOREIGN KEY (teacher_id) REFERENCES teachers(id) ON DELETE CASCADE,
    CONSTRAINT fk_teacher_subjects_subject
        FOREIGN KEY (subject_id) REFERENCES subjects(id) ON DELETE CASCADE
);

-- ENROLLMENTS
CREATE TABLE IF NOT EXISTS enrollments (
    id         BIGINT       PRIMARY KEY DEFAULT nextval('enrollments_seq'),
    student_id BIGINT       NOT NULL,
    subject_id VARCHAR(255) NOT NULL,
    semester   INTEGER      NOT NULL,
    grade      REAL,
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT uq_enrollments_student_subject_semester UNIQUE (student_id, subject_id, semester),
    CONSTRAINT fk_enrollments_student
        FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
    CONSTRAINT fk_enrollments_subject
        FOREIGN KEY (subject_id) REFERENCES subjects(id) ON DELETE CASCADE
);

-- INDEXES
CREATE INDEX IF NOT EXISTS idx_enrollments_student_id      ON enrollments(student_id);
CREATE INDEX IF NOT EXISTS idx_enrollments_subject_id      ON enrollments(subject_id);
CREATE INDEX IF NOT EXISTS idx_teacher_subjects_subject_id ON teacher_subjects(subject_id);
CREATE INDEX IF NOT EXISTS idx_subject_prereq_subject_id   ON subject_prerequisites(subject_id);
