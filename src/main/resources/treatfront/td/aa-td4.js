import * as THREE from 'three';
import { OrbitControls } from 'three/addons/OrbitControls.js';
// import { PLYLoader } from 'three/addons/loaders/PLYLoader.js';
import {STLLoader} from 'three/addons/STLLoader.js'

//场


const width = 350
const height = 350
const scene = new THREE.Scene();
const camera = new THREE.PerspectiveCamera(30, 350 / 350, 1, 3000);
const renderer = new THREE.WebGLRenderer();
renderer.setSize(window.innerWidth, window.innerHeight);
document.body.appendChild(renderer.domElement);


window.loadModel4=function(filepath) {

	// 清空场景中的旧模型
	while (scene.children.length > 0) {
		scene.remove(scene.children[0]);
	}

	const loader = new STLLoader();
	const model = new THREE.Group();
	const modelSize = new THREE.Vector3();
	const scaleFactor = 0;
	const modelCenter = new THREE.Vector3();
	loader.load(filepath, function (ply) {
		// ply.computeVertexNormals();
		ply.computeBoundingBox();
		const material = new THREE.MeshStandardMaterial({
			flatShading: true,
			color: 0xff0000,
			wireframe: false,
			side: THREE.DoubleSide,
			// color: 0x00ffff,
		});
		const mesh = new THREE.Mesh(ply, material);
		// 计算模型的边界框
		const boundingBox = new THREE.Box3().setFromObject(mesh);

		// 计算模型的中心位置
		// const modelCenter = new THREE.Vector3();
		modelCenter.addVectors(boundingBox.min, boundingBox.max).multiplyScalar(0.5);

		// 计算模型的尺寸
		// const modelSize = new THREE.Vector3();
		modelSize.subVectors(boundingBox.max, boundingBox.min);
		// 计算模型的缩放比例
		const scaleFactor = 30 / Math.max(modelSize.x, modelSize.y, modelSize.z);
		// 设置模型的位置和缩放
		mesh.position.copy(modelCenter.multiplyScalar(-scaleFactor));
		mesh.scale.set(scaleFactor, scaleFactor, scaleFactor);
		// 将模型移动到原点
		// mesh.position.sub(center);
		//  mesh.position.set(0,0,0);
		// const rotationAxis = new THREE.Vector3(1, 0, 0); // 绕 X 轴旋转
		// const rotationAngle = Math.PI / 2; // 旋转角度，这里是 90 度
		// mesh.rotateOnWorldAxis(rotationAxis, rotationAngle);
		// mesh.scale.set(0.05,0.05,0.05);
		console.log('控制台查看ply对象结构', ply);
		console.log('场景3D模型数据', ply.scene);
		const parent = new THREE.Object3D();
		scene.add(parent);
		model.add(mesh);
		parent.add(model);
		parent.rotation.x = -Math.PI/2;
	})


	scene.add(model); //模型对象添加到场景中


//辅助观察的坐标系
	const axesHelper = new THREE.AxesHelper(100);
	scene.add(axesHelper);


//光源设置
// 	const directionalLight = new THREE.DirectionalLight(0xffffff, 1);
// 	directionalLight.position.set(100, 60, 50);
// 	scene.add(directionalLight);
// 	const ambient = new THREE.AmbientLight(0xffffff, 0.4);
// 	scene.add(ambient);


	const hemiLight = new THREE.HemisphereLight( 0xffffff, 0x000000, 1 );
	scene.add( hemiLight );

	const dirLight = new THREE.DirectionalLight( 0xffffff, 0.8 );
	dirLight.position.set( -200,-300,-200 );
	scene.add( dirLight );




//渲染器和相机

	// const camera = new THREE.PerspectiveCamera(45, width / height, 1, 2000);
	camera.position.set(-60,0 , 0);
	camera.lookAt(0, 300, 0);
// 设置相机位置
// 	const fov = 30; // 视角角度
// 	 const camera = new THREE.PerspectiveCamera(fov, width / height, 1, 3000);
// 	const width = 350
// 	const height = 350
// 	const cameraDistance = modelSize.length() * scaleFactor * 2; // 根据模型尺寸和缩放计算相机距离模型的距离
// 	const aspectRatio=width/height;
// 	const near = modelSize.length() * scaleFactor / modelSize.length() * scaleFactor; // 设置近截面距离
// 	const far = modelSize.length() * scaleFactor * 100; // 设置近截面距离
// 	const camera = new THREE.PerspectiveCamera(fov, aspectRatio, near, far);
// 	camera.position.set(cameraDistance, 0, 0);
// 	camera.lookAt(modelCenter);

	// const renderer = new THREE.WebGLRenderer();
	renderer.setSize(width, height);
	renderer.antialias = true; // 开启抗锯齿
	document.getElementById('td4').appendChild(renderer.domElement);


// 渲染循环
	function render() {
		renderer.render(scene, camera);
		requestAnimationFrame(render);
	}

	render();

	const controls = new OrbitControls(camera, renderer.domElement);

	// document.getElementById('vartd11').append("8e418a8f-7517-4aa8-8846-dd4f58e2dccc.nii")
	// document.getElementById('vartd12').append("1号")
	// document.getElementById('vartd13').append("脾脏")
	//
	// document.getElementById('vartd21').append("8e418a8f-7517-4aa8-8846-dd4f58e2dccc.nii")
	// document.getElementById('vartd22').append("2号")
	// document.getElementById('vartd23').append("右肾")
	//
	// document.getElementById('vartd31').append("8e418a8f-7517-4aa8-8846-dd4f58e2dccc.nii")
	// document.getElementById('vartd32').append("3号")
	// document.getElementById('vartd33').append("左肾")
	//
	// document.getElementById('vartd41').append("8e418a8f-7517-4aa8-8846-dd4f58e2dccc.nii")
	// document.getElementById('vartd42').append("4号")
	// document.getElementById('vartd43').append("胆囊")

}


