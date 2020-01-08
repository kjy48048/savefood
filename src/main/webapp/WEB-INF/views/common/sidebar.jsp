<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<ul class="sidebar navbar-nav">
	<li class="nav-item sidebar-home"><a class="nav-link" href="/">
			<i class="fas fa-fw fa-tachometer-alt"></i>
			<span>HOME</span>
	</a></li>
	<li class="nav-item"><a class="nav-link" href="/view/fridge" id="pagesDropdown" role="button" aria-haspopup="true" aria-expanded="false">
			<i class="fas fa-fw fa-folder"></i>
			<span>MY 냉장고</span>
		</a>
	</li>
	<li class="nav-item"><a class="nav-link" href="/view/food" id="pagesDropdown" role="button" aria-haspopup="true" aria-expanded="false">
			<i class="fas fa-fw fa-folder"></i>
			<span>식품등록</span>
		</a></li>
	<!-- <li class="nav-item"><a class="nav-link" href="#"> <i class="fas fa-fw fa-chart-area"></i> <span>Charts</span></a></li>
	<li class="nav-item"><a class="nav-link" href="#"> <i class="fas fa-fw fa-table"></i> <span>Tables</span></a></li> -->
</ul>

<script>
	window.onload = function(){
		var path = location.pathname;
		var target;
		if(path.startsWith("/view/fridge")){
				target = document.querySelector('a[href="/view/fridge"]').parentElement;
			}
		else if(path.startsWith("/view/food")){
				target = document.querySelector('a[href="/view/food"]').parentElement;
			}
		else{
			target = document.querySelector('.sidebar-home');
		}
		target.style.background = 'orange';
		target.className += " active";
	}
	
</script>