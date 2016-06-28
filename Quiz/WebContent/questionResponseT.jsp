
	<form action="CreateQuestion" method="post">
		<p>Write the question</p>
		<textarea rows="4" cols="50" name=question></textarea>
		<p>Write the answer</p>
		<textarea rows="1" cols="50" name="answer"></textarea>
		<br></br>
		<input class="timed" type="checkbox" name="timed"
			placeholder="Allow" />Timed?<br />
		<input type="hidden" name="type" value="QR">
		<button name="next">Add question</button>
		
	</form>
	<form action="FinishQuiz" method="post">
		<button name="done" >No more questions</button>
	</form>




