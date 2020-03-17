//for review site version 3
const tagAddButton = document.querySelector('.add-tag button'); //class add-tag, element button
const tagAddInput = document.querySelector('.add-tag input');
const tagsList = document.querySelector('.tags-list ul');

const xhr = new XMLHttpRequest()
xhr.onreadystatechange = function(){
	if(xhr.readyState === 4 && xhr.status === 200){
		const res = xhr .responseText;
		tagsList.innerHTML = res;
		}
}

tagAddButton.addEventListener('click', function(){
	posttags(tagAddInput.value);
	console.log(tagAddInput.value);
	tagAddInput.value = ""; //clear

})
	
function posttags(tagName){
	xhr.open('POST', 'tags/' + tagName, true);
	xhr.send();
}

//remove tags