package ru.sber.functional

class StudentsGroup(firstName: List<String>,lastName: List<String>, averageRate: List<Double>) {

    var students: List<Student>

    fun filterByPredicate(value: (Student) -> Boolean): List<Student> {
        if(students != null){
            return students.filter{oneStudent -> value(oneStudent)}
        }
        return listOf()
    }

    init {
        val group = ArrayList<Student>()
        for(i in firstName.indices){
            group.add(Student(
                firstName = firstName[i],
                lastName = lastName[i],
                averageRate = averageRate[i]
            ))
        }
        students = group
    }
}
