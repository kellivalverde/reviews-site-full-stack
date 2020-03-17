const xhr = new XMLHttpRequest();
xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            //console.log(xhr.responseText);
            const res = JSON.parse(xhr.response); //could use let instead of const here
            const container = document.querySelector('.container');
            console.log({
                res
            });

            res.forEach(function (course) {  //annonymouse function
                    const courseItem = document.createElement('div')

                    const name = document.createElement('h2')
                    name.innerText = course.name;

                    const description = document.createElement('p')
                    description.innerText = course.description

                    const topics = []; //an array
                    course.topicsUrls.forEach(topicURL => {  //iterate over each one
                        const topicUrlElement = document.createElement('li') // displays my topics as bullet points
                        topicUrlElement.innerHTML = `course topics: <a href="${topicURL}">${topicURL}</a>`
                        topics.push(topicUrlElement)
                    })

                    container.appendChild(courseItem);
                    courseItem.appendChild(name);
                    courseItem.appendChild(description);
                    topics.forEach(topicURL => courseItem.appendChild(topicURL)) 
                    //tied to my Course Object in Java
                });


            }
        }
        xhr.open('GET', 'http://localhost:8080/api/courses', true); //talks to my REST controller
        xhr.send();